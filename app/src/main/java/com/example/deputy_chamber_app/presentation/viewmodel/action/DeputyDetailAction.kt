package com.example.deputy_chamber_app.presentation.viewmodel.action

import com.example.deputy_chamber_app.domain.entity.DeputyDetail

sealed class DeputyDetailAction {
    data class LoadData(val deputyId: Int) : DeputyDetailAction()
    data class DataLoaded(val data: DeputyDetail) : DeputyDetailAction()
    data class Error(val error: String) : DeputyDetailAction()
}