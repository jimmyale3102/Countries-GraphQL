package dev.alejo.countriesgraphql.domain.usecases

import dev.alejo.countriesgraphql.domain.CountryClient
import dev.alejo.countriesgraphql.domain.model.CountriesModel
import dev.alejo.countriesgraphql.domain.model.CountryModel

class GetCountryUseCase(private val countryClient: CountryClient) {
    suspend operator fun invoke(countryCode: String): CountryModel? = countryClient
        .getCountryByCode(countryCode)
}