package dev.alejo.countriesgraphql.presentation.home

import android.widget.Space
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import dev.alejo.countriesgraphql.domain.model.CountryModel
import dev.alejo.countriesgraphql.domain.usecases.CountriesState

@Composable
fun HomeScreen(
    state: CountriesState,
    onSelectCountry: (code: String) -> Unit,
    onDismissCountryDialog: () -> Unit
) {
    AnimatedVisibility(visible = state.isLoading) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            CircularProgressIndicator()
        }
    }
    AnimatedVisibility(visible = !state.isLoading) {
        LazyColumn(Modifier.fillMaxSize(), contentPadding = PaddingValues(16.dp)) {
            items(state.countries) {
                ListItem(
                    modifier = Modifier
                        .clickable {
                            onSelectCountry(it.code)
                        },
                    headlineContent = {
                        Text(text = it.name)
                    },
                    supportingContent = {
                        Text(text = it.capital)
                    },
                    leadingContent = {
                        Text(text = it.emoji, fontSize = 32.sp)
                    }
                )
            }
        }

        if (state.selectedCountry != null) {
            CountryDialog(
                countrySelected = state.selectedCountry,
                onDismiss = onDismissCountryDialog
            )
        }
    }
}

@Composable
fun CountryDialog(countrySelected: CountryModel, onDismiss: () -> Unit) {
    val languages = remember(countrySelected.languages) {
        countrySelected.languages.joinToString()
    }
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.background)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(Modifier.fillMaxWidth()) {
                Text(text = countrySelected.emoji, fontSize = 32.sp)
                Text(text = countrySelected.name, fontSize = 24.sp)
            }
            Spacer(Modifier.height(16.dp))
            Text(text = "Continent: " + countrySelected.continent)
            Text(text = "Currency: " + countrySelected.currency)
            Text(text = "Capital: " + countrySelected.capital)
            Text(text = "Languages: $languages")
        }
    }
}