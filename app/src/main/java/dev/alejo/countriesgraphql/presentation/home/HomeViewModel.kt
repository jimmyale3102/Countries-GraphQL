package dev.alejo.countriesgraphql.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.alejo.countriesgraphql.domain.usecases.CountriesState
import dev.alejo.countriesgraphql.domain.usecases.GetCountriesUseCase
import dev.alejo.countriesgraphql.domain.usecases.GetCountryUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getCountriesUseCase: GetCountriesUseCase,
    private val getCountryUseCase: GetCountryUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(CountriesState())
    var state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            _state.update { it.copy(countries = getCountriesUseCase(), isLoading = false) }
            Log.d("Countries->", _state.value.countries.toString())
        }
    }

    fun selectCountry(countryCode: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(selectedCountry = getCountryUseCase(countryCode))
            }
        }
    }

    fun dismissCountryDialog() {
        _state.update { it.copy(selectedCountry = null) }
    }
}