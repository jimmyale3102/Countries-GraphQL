package dev.alejo.countriesgraphql.domain.usecases

import dev.alejo.countriesgraphql.domain.CountryClient
import dev.alejo.countriesgraphql.domain.model.CountriesModel

class GetCountriesUseCase(private val countryClient: CountryClient) {
    suspend operator fun invoke(): List<CountriesModel> = countryClient
        .getCountries()
        .sortedBy { it.name }
}