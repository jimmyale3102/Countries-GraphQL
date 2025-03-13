package dev.alejo.countriesgraphql.domain

import dev.alejo.countriesgraphql.domain.model.CountriesModel
import dev.alejo.countriesgraphql.domain.model.CountryModel

interface CountryClient {
    suspend fun getCountries(): List<CountriesModel>
    suspend fun getCountryByCode(countryCode: String): CountryModel?
}