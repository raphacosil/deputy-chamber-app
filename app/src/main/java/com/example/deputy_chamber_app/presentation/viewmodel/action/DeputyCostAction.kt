package com.example.deputy_chamber_app.presentation.viewmodel.action

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.CostItem
import kotlinx.coroutines.flow.Flow

sealed class DeputyCostAction {
    data class  LoadData(val deputyId: Int) : DeputyCostAction()
    data class DataLoaded(val data: Flow<PagingData<CostItem>>) : DeputyCostAction()
    data class Error(val error: String) : DeputyCostAction()
}