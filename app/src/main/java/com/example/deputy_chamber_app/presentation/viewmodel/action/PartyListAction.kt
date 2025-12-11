package com.example.deputy_chamber_app.presentation.viewmodel.action

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.PartyItem
import kotlinx.coroutines.flow.Flow

sealed class PartyListAction {
    data object LoadData : PartyListAction()
    data class DataLoaded(val data: Flow<PagingData<PartyItem>>) : PartyListAction()
    data class Error(val error: String) : PartyListAction()
}