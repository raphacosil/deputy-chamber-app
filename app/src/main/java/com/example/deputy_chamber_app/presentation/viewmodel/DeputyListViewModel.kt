package com.example.deputy_chamber_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import com.example.deputy_chamber_app.domain.usecase.GetDeputiesUseCase
import com.example.deputy_chamber_app.presentation.viewmodel.action.DeputyListAction
import com.example.deputy_chamber_app.presentation.viewmodel.state.DeputyListState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DeputyListViewModel(
    private val getDeputiesUseCase: GetDeputiesUseCase,
): ViewModel() {
    private val _deputyListState = MutableLiveData<DeputyListState>()
    val deputyListState: LiveData<DeputyListState> = _deputyListState
    init {
        _deputyListState.value = DeputyListState()
    }

    fun handleAction(action: DeputyListAction) {
        when (action) {
            is DeputyListAction.LoadData -> loadData()
            is DeputyListAction.DataLoaded -> onDataLoaded(action.data)
            is DeputyListAction.Error -> onError(action.error)
        }
    }

    private fun loadData() {
        _deputyListState.value = _deputyListState.value?.copy(isLoading = true)
        getDeputyList()
    }

    private fun onDataLoaded(data: Flow<PagingData<DeputyItem>>) {
        _deputyListState.value = _deputyListState.value?.copy(isLoading = false, data = data)
    }

    private fun onError(error: String) {
        _deputyListState.value = _deputyListState.value?.copy(isLoading = false, errorMessage = error)
    }

    private fun getDeputyList() {
        viewModelScope.launch {
            try {
                val result = getDeputiesUseCase.invoke(20).cachedIn(this)
                onDataLoaded(result)
            } catch (e: Exception) {
                Log.e("DeputyListViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}