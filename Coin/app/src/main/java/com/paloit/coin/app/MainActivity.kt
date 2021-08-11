package com.paloit.coin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.paloit.coin.R
import com.paloit.coin.ui.theme.CoinTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel: MainActivityViewModel = viewModel()
            val uiState by viewModel.uiState.observeAsState(MainActivityUiState(false, ""))

            CoinTheme {
                // A surface container using the 'background' color from the theme
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
                        ) { viewModel.refreshBitcoinPrice() }
                    }
                }
            }
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
            text = "$price",
            color = MaterialTheme.colors.primary,
            style = MaterialTheme.typography.caption
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = onRefreshClicked) {
            Text("Refresh")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinTheme {
        BitcoinPrice(true, "10,000,000") {}
    }
}
