package dev.alejo.countriesgraphql.data

import dev.alejo.CountriesQuery
import dev.alejo.CountryQuery
import dev.alejo.countriesgraphql.domain.model.CountriesModel
import dev.alejo.countriesgraphql.domain.model.CountryModel

fun CountryQuery.Country.toCountryModel(): CountryModel {
    return CountryModel(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital",
        currency = currency ?: "No currency",
        continent = continent.name,
        languages = languages.map { it.name }
    )
}

fun CountriesQuery.Country.toCountriesModel(): CountriesModel {
    return CountriesModel(
        code = code,
        name = name,
        emoji = emoji,
        capital = capital ?: "No capital"
    )
}