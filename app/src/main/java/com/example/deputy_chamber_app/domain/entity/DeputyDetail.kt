package com.example.deputy_chamber_app.domain.entity

data class DeputyDetail(
    val id: Int,
    val civilName: String,
    val party: String,
    val uf: String,
    val imageUrl: String,

    val situation: String,

    val birthDate: String,
    val birthState: String,
    val birthMunicipality: String,
    val schooling: String,

    val name: String,
    val building: String,
    val room: String,
    val floor: String,
    val phone: String,
    val email: String?,

    val socialMedia: List<String>?,
)
