package com.example.deputy_chamber_app.domain.entity

data class DeputyItem(
    val id: Int,
    val name: String,
    val email: String,
    val party: String,
    val uf: String,
    val imageUrl: String
)
