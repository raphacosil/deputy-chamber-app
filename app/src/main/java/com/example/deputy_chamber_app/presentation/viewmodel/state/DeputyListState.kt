package com.example.deputy_chamber_app.presentation.viewmodel.state

import com.example.deputy_chamber_app.domain.entity.DeputyItem

data class DeputyListState (
    val isLoading: Boolean = false,
    var data: List<DeputyItem> = emptyList(),
    val errorMessage: String? = null
)