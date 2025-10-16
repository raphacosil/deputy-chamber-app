package com.example.deputy_chamber_app.presentation.viewmodel.action

import androidx.paging.PagingData
import com.example.deputy_chamber_app.domain.entity.DeputyItem
import kotlinx.coroutines.flow.Flow

sealed class DeputyListAction  {
    data object LoadData : DeputyListAction()
    data class DataLoaded(val data: Flow<PagingData<DeputyItem>>) : DeputyListAction()
    data class Error(val error: String) : DeputyListAction()
}