package com.example.deputy_chamber_app.domain.entity

data class CostItem(
    val type: String,
    val supplier: String,
    val supplierCpfCnpj: String,
    val installment: Int,
    val value: Double,
    val documentType: String,
    val documentLink: String
)
