package dev.alejo.countriesgraphql.domain.model

data class CountryModel(
    val code: String,
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val continent: String,
    val languages: List<String>
)