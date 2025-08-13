package com.example.deputy_chamber_app.data.dto

import com.google.gson.annotations.SerializedName

data class GetDeputiesResponse(
    @SerializedName("dados")
    val data: List<DeputyItemDto>,
    @SerializedName("links")
    val links: List<LinkDto>
)

data class DeputyItemDto(
    val id: Int,
    val uri: String,
    @SerializedName("nome")
    val name: String,
    @SerializedName("siglaPartido")
    val party: String,
    @SerializedName("uriPartido")
    val uriParty: String,
    @SerializedName("siglaUf")
    val uf: String,
    @SerializedName("idLegislatura")
    val idLegislature: Int,
    @SerializedName("urlFoto")
    val urlPhoto: String,
    val email: String
)
