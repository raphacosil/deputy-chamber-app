package com.example.deputy_chamber_app.presentation.viewmodel.state

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.CostItem
import kotlinx.coroutines.flow.Flow

data class DeputyCostState (
    val isLoading: Boolean = false,
    var data: Flow<PagingData<CostItem>>? = null,
    val errorMessage: String? = null
)