package com.example.deputy_chamber_app.presentation.viewmodel.state

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.PartyItem
import kotlinx.coroutines.flow.Flow

data class PartyListState (
    val isLoading: Boolean = false,
    var data: Flow<PagingData<PartyItem>>? = null,
    val errorMessage: String? = null
)