package dev.alejo.countriesgraphql.domain.model

data class CountriesModel(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String
)