package com.example.deputy_chamber_app.presentation.viewmodel.state

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import kotlinx.coroutines.flow.Flow

data class DeputyListState (
    val isLoading: Boolean = false,
    var data: Flow<PagingData<DeputyItem>>? = null,
    val errorMessage: String? = null
)