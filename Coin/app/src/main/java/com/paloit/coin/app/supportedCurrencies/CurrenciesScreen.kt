package com.paloit.coin.app.supportedCurrencies

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NavigateNext
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.tooling.preview.Preview
import com.paloit.coin.R
import com.paloit.coin.app.LoadingIndicator
import com.paloit.coin.platform.repository.data.Currency

@ExperimentalMaterialApi
@Composable
fun ListScreen(uiState: CurrenciesUiState, onClick: () -> Unit) {

    if (uiState.isListLoading) {
        LoadingIndicator()
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        CurrenciesList(uiState.itemsList, onClick)
    }
}

@Composable
fun CurrenciesList(items: List<Currency>, onClick: () -> Unit) {
    LazyColumn(contentPadding = PaddingValues(start = dimensionResource(id = R.dimen.list_start_padding))) {
        items(items = items) { item ->
            ListItem(item, onClick)
        }
    }
}

@Composable
fun ListItem(item: Currency, onClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(item.currency)
        Text(item.country)
        IconButton(onClick = onClick) {
            Icon(
                Icons.Filled.NavigateNext,
                null
            )
        }
    }
}

@ExperimentalMaterialApi
@Preview(showBackground = true)
@Composable
fun ListScreenPreview() {
    val currencies = listOf(
        Currency("Australia", "AUD"),
        Currency("USA", "USD"),
        Currency("United Kingdom", "GBP")
    )
    ListScreen(CurrenciesUiState(false, currencies)) {}
}
