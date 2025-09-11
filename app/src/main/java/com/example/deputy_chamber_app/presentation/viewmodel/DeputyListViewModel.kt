package com.example.deputy_chamber_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deputy_chamber_app.domain.DeputyPagingSource
import com.example.deputy_chamber_app.domain.entity.DeputiesPage
import com.example.deputy_chamber_app.domain.usecase.GetDeputiesUseCase
import com.example.deputy_chamber_app.presentation.viewmodel.action.DeputyListAction
import com.example.deputy_chamber_app.presentation.viewmodel.state.DeputyListState
import kotlinx.coroutines.launch

class DeputyListViewModel(
    private val getDeputiesUseCase: GetDeputiesUseCase,
    private val deputyPagingSource: DeputyPagingSource
): ViewModel() {
    private val _deputyListState = MutableLiveData<DeputyListState>()
    val deputyListState: LiveData<DeputyListState> = _deputyListState
    init {
        _deputyListState.value = DeputyListState()
    }

    fun handleAction(action: DeputyListAction) {
        when (action) {
            is DeputyListAction.LoadData -> loadData(action.page)
            is DeputyListAction.DataLoaded -> onDataLoaded(action.data)
            is DeputyListAction.Error -> onError(action.error)
        }
    }

    private fun loadData(page: Int?) {
        _deputyListState.value = _deputyListState.value?.copy(isLoading = true)
        getDeputyList(page)
    }

    private fun onDataLoaded(data: DeputiesPage) {
        _deputyListState.value = _deputyListState.value?.copy(isLoading = false, data = data)
    }

    private fun onError(error: String) {
        _deputyListState.value = _deputyListState.value?.copy(isLoading = false, errorMessage = error)
    }

    private fun getDeputyList(page: Int?) {
        viewModelScope.launch {
            try {
                val result = deputyPagingSource.load(null)
                if (result != null) {
                    onDataLoaded(result)
                } else {
                    Log.e("DeputyListViewModel",
                        "Error fetching todos | MESSAGE Empty response"
                    )
                    onError("Empty response")
                }
            } catch (e: Exception) {
                Log.e("DeputyListViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}