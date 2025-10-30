package com.example.deputy_chamber_app.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.deputy_chamber_app.domain.entity.CostItem
import com.example.deputy_chamber_app.domain.usecase.GetDeputyCostsUseCase
import com.example.deputy_chamber_app.presentation.viewmodel.action.DeputyCostAction
import com.example.deputy_chamber_app.presentation.viewmodel.state.DeputyCostState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class DeputyCostViewModel(
    private val getDeputyCostsUseCase: GetDeputyCostsUseCase,
): ViewModel() {
    private val _deputyCostState = MutableLiveData<DeputyCostState>()
    val deputyCostState: LiveData<DeputyCostState> = _deputyCostState
    init {
        _deputyCostState.value = DeputyCostState()
    }

    fun handleAction(action: DeputyCostAction) {
        when (action) {
            is DeputyCostAction.LoadData -> loadData(action.deputyId)
            is DeputyCostAction.DataLoaded -> onDataLoaded(action.data)
            is DeputyCostAction.Error -> onError(action.error)
        }
    }

    private fun loadData(deputyId: Int) {
        Log.d("DeputyListViewModel", "loadData: ")
        _deputyCostState.value = _deputyCostState.value?.copy(isLoading = true)
        getDeputyCosts(deputyId)
    }

    private fun onDataLoaded(data: Flow<PagingData<CostItem>>) {
        Log.d("DeputyListViewModel", "onDataLoaded: $data")
        _deputyCostState.value = _deputyCostState.value?.copy(isLoading = false, data = data)
    }

    private fun onError(error: String) {
        Log.d("DeputyListViewModel", "onError: $error")
        _deputyCostState.value = _deputyCostState.value?.copy(isLoading = false, errorMessage = error)
    }

    private fun getDeputyCosts(deputyId: Int) {
        viewModelScope.launch {
            try {
                val result = getDeputyCostsUseCase.invoke(deputyId, 15).cachedIn(this)
                Log.d("DeputyCostViewModel", "getDeputyCosts: $result")
                onDataLoaded(result)
            } catch (e: Exception) {
                Log.e("DeputyCostViewModel",
                    "Error fetching todos | MESSAGE ${e.message} | CAUSE ${e.cause}"
                )
                onError(e.message.toString())
            }
        }
    }
}
