package com.example.deputy_chamber_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deputy_chamber_app.domain.entity.DeputyDetail
import com.example.deputy_chamber_app.domain.usecase.GetDeputyDetailUseCase
import com.example.deputy_chamber_app.presentation.viewmodel.action.DeputyDetailAction
import com.example.deputy_chamber_app.presentation.viewmodel.state.DeputyDetailState
import kotlinx.coroutines.launch

class DeputyDetailViewModel(
    private val getDeputyDetailUseCase: GetDeputyDetailUseCase
): ViewModel() {
    private val _deputyDetailState = MutableLiveData<DeputyDetailState>()
    val deputyDetailState: LiveData<DeputyDetailState> = _deputyDetailState

    init {
        _deputyDetailState.value = DeputyDetailState()
    }

    fun handleAction(action: DeputyDetailAction) {
        when (action) {
            is DeputyDetailAction.LoadData -> loadData(action.deputyId)
            is DeputyDetailAction.DataLoaded -> onDataLoaded(action.data)
            is DeputyDetailAction.Error -> onError(action.error)
        }
    }

    private fun loadData(deputyId: Int) {
        _deputyDetailState.value = _deputyDetailState.value?.copy(isLoading = true)
        getDeputyDetail(deputyId)
    }

    private fun onDataLoaded(data: DeputyDetail?) {
        _deputyDetailState.value = _deputyDetailState.value?.copy(
            isLoading = false,
            data = data
        )
    }

    private fun onError(error: String) {
        _deputyDetailState.value = _deputyDetailState.value?.copy(
            isLoading = false,
            errorMessage = error
        )
        Log.e(
            "DeputyDetailViewModel",
            "Error fetching todos | MESSAGE $error"
        )
    }

    private fun getDeputyDetail(deputyId: Int) {
        viewModelScope.launch {
            try {
                Log.d("DeputyDetailViewModel", "getDeputyDetail: $deputyId")
                val result = getDeputyDetailUseCase.invoke(deputyId)
                onDataLoaded(result)
            } catch (e: Exception) {
                Log.e(
                    "DeputyDetailViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}