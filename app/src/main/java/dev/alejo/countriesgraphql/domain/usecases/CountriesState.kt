package dev.alejo.countriesgraphql.domain.usecases

import dev.alejo.countriesgraphql.domain.model.CountriesModel
import dev.alejo.countriesgraphql.domain.model.CountryModel

data class CountriesState(
    val countries: List<CountriesModel> = emptyList(),
    val isLoading: Boolean = false,
    val selectedCountry: CountryModel? = null
)