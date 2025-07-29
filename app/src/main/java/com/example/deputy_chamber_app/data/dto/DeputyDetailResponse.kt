package com.example.deputy_chamber_app.data.dto

import com.google.gson.annotations.SerializedName

data class DeputyDetailResponse(
    @SerializedName("dados")
    val data: DeputyInfo,
    @SerializedName("links")
    val links: List<LinkDto>
)

data class DeputyInfo(
    val id: Int,
    val uri: String,
    @SerializedName("nomeCivil")
    val civilName: String,
    @SerializedName("siglaPartido")
    val party: String,
    @SerializedName("uriPartido")
    val uriParty: String,
    val uf: String,
    @SerializedName("idLegislatura")
    val idLegislature: Int,
    @SerializedName("urlFoto")
    val urlPhoto: String,
    val email: String,
    @SerializedName("nomeEleitoral")
    val dataNascimento: String,
    @SerializedName("ufNascimento")
    val birthState: String,
    @SerializedName("municipioNascimento")
    val birthMunicipality: String,
    @SerializedName("escolaridade")
    val schooling: String,
    @SerializedName("redeSocial")
    val socialMedia: List<String>,
    @SerializedName("ultimoStatus")
    val lastStatus: ElectoralInfo,
)

data class ElectoralInfo(
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
    val email: String,
    @SerializedName("nomeEleitoral")
    val electoralName: String,
    @SerializedName("situacao")
    val situation: String,
    @SerializedName("condicaoEleitoral")
    val electoralCondition: String,
    @SerializedName("descricaoStatus")
    val description: String,
    @SerializedName("gabinete")
    val cabinet: CabinetInfo
)

data class CabinetInfo(
    @SerializedName("nome")
    val name: String,
    @SerializedName("predio")
    val building: String,
    @SerializedName("sala")
    val room: String,
    @SerializedName("andar")
    val floor: String,
    @SerializedName("telefone")
    val phone: String,
    @SerializedName("email")
    val email: String
)