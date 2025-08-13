package com.example.deputy_chamber_app.data.dto

import com.google.gson.annotations.SerializedName

data class DeputyCostsResponse(
    @SerializedName("dados")
    val data: List<CostItemDto>,
    @SerializedName("links")
    val links: List<LinkDto>
)

data class CostItemDto(
    @SerializedName("tipoDespesa")
    val type: String,
    @SerializedName("nomeFornecedor")
    val supplier: String,
    @SerializedName("cnpjCpfFornecedor")
    val supplierCpfCnpj: String,
    @SerializedName("parcela")
    val installment: Int,
    @SerializedName("valorLiquido")
    val value: Double,
    @SerializedName("tipoDocumento")
    val documentType: String,
    @SerializedName("urlDocumento")
    val documentLink: String
)
