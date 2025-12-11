package com.example.deputy_chamber_app.domain.entity

data class PartyDetail(
    val id: Int,
    val acronym: String,
    val name: String,
    val state: String,
    val status: String,
    val logoUrl: String?,

    val leaderName: String,
    val leaderPhotoUrl: String?,

    val totalMembers: Int,
    val membersInOffice: Int,
    val foundedIn: String
)
