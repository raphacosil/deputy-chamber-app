package com.example.deputy_chamber_app

fun String?.capitalize(): String {
    if (this.isNullOrBlank()) return ""
    return this.split(" ").joinToString(" ") {
        it.lowercase().replaceFirstChar { c -> c.uppercaseChar() }
    }
}