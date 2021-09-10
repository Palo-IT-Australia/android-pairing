package com.paloit.coin.app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.paloit.coin.R

@Composable
fun BitcoinScreen(uiState: MainActivityUiState, onRefreshClicked: () -> Unit) {
    Surface(color = MaterialTheme.colors.background) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Sponsor()
            Spacer(modifier = Modifier.height(32.dp))
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
                contentDescription = "Bitcoin icon"
            )
            if (isLoading) {
                CircularProgressIndicator()
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = price,
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRefreshClicked) {
            Text("Refresh")
        }
    }
}