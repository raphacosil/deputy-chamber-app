package com.example.deputy_chamber_app.presentation.viewmodel.action

import com.example.deputy_chamber_app.domain.entity.DeputyItem

sealed class DeputyListAction  {
    data class LoadData(val page: Int?) : DeputyListAction()
    data class DataLoaded(val data: List<DeputyItem>) : DeputyListAction()
    data class Error(val error: String) : DeputyListAction()
}