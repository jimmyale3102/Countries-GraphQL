package dev.alejo.countriesgraphql.data

import com.apollographql.apollo.ApolloClient
import dev.alejo.CountriesQuery
import dev.alejo.CountryQuery
import dev.alejo.countriesgraphql.domain.CountryClient
import dev.alejo.countriesgraphql.domain.model.CountriesModel
import dev.alejo.countriesgraphql.domain.model.CountryModel

class ApolloCountryClient(
    private val apolloClient: ApolloClient
) : CountryClient{
    override suspend fun getCountries(): List<CountriesModel> {
        return apolloClient.query(CountriesQuery())
            .execute()
            .data
            ?.countries
            ?.map { it.toCountriesModel() }
            .orEmpty()
    }

    override suspend fun getCountryByCode(countryCode: String): CountryModel? {
        return apolloClient.query(CountryQuery(countryCode))
            .execute()
            .data
            ?.country
            ?.toCountryModel()
    }
}