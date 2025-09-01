package com.example.deputy_chamber_app.domain.entity

data class DeputiesPage(
    val itemList: List<DeputyItem>,
    val previousPage: Int,
    val nextPage: Int
)