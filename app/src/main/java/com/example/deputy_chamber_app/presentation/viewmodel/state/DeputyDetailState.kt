package com.example.deputy_chamber_app.presentation.viewmodel.state

import com.example.deputy_chamber_app.domain.entity.DeputyDetail

data class DeputyDetailState  (
    val isLoading: Boolean = false,
    var data: DeputyDetail? = null,
    val errorMessage: String? = null
)