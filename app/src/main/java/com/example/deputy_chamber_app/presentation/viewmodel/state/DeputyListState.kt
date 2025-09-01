package com.example.deputy_chamber_app.presentation.viewmodel.state

import com.example.deputy_chamber_app.domain.entity.DeputiesPage

data class DeputyListState (
    val isLoading: Boolean = false,
    var data: DeputiesPage? = null,
    val errorMessage: String? = null
)