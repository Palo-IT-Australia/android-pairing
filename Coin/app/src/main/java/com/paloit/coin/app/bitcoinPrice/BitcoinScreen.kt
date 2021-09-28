package com.paloit.coin.app.bitcoinPrice

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.paloit.coin.R

@Composable
fun BitcoinScreen(uiState: BitcoinPriceUiState, onRefreshClicked: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Sponsor()
            Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_height_full)))
            BitcoinPrice(
                isLoading = uiState.isBitcoinPriceLoading,
                price = uiState.bitcoinPrice
            ) { onRefreshClicked() }
        }
    }
}

@Composable
fun Sponsor() {
    Column {
        Text(text = "Powered by CoinDesk")
        Text(text = "https://www.coindesk.com/price/bitcoin")
    }
}

@Composable
fun BitcoinPrice(isLoading: Boolean, price: String, onRefreshClicked: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Box(contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.ic_bitcoin_cute),
                contentDescription = stringResource(id = R.string.icon_bitcoin_description)
            )
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_height_half)))
        Text(
            text = price,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer_height_half)))
        Button(onClick = onRefreshClicked) {
            Text(stringResource(id = R.string.button_refresh))
        }
    }
}
