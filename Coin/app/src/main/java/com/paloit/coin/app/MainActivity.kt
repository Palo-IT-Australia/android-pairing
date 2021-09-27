package com.paloit.coin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.paloit.coin.app.bitcoinPrice.BitcoinPrice
import com.paloit.coin.app.bitcoinPrice.BitcoinPriceUiState
import com.paloit.coin.app.bitcoinPrice.BitcoinScreen
import com.paloit.coin.app.bitcoinPrice.BitcoinViewModel
import com.paloit.coin.app.supportedCurrencies.CurrenciesViewModel
import com.paloit.coin.app.supportedCurrencies.ListScreen
import com.paloit.coin.ui.theme.CoinTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = listOf(Screens.BitcoinPage, Screens.ListPage)

        setContent {
            val navController = rememberNavController()

            CoinTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(bottomBar = {
                    AppBottomNavBar(items = items, navController)
                }) {
                    AppNavHost(navController = navController)
                }
            }
        }
    }
}

@Composable
fun AppBottomNavBar(items: List<Screens>, navController: NavController) {
    BottomNavigation {
        val navStackBackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navStackBackEntry?.destination?.route
        items.forEach { screen ->
            BottomNavigationItem(
                icon = { Icon(screen.icon, stringResource(id = screen.iconDescription)) },
                label = { Text(stringResource(id = screen.resourceId)) },
                selected = currentRoute == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        launchSingleTop = true
                        restoreState = true
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                    }
                })
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun AppNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Screens.BitcoinPage.route) {
        composable(Screens.BitcoinPage.route) {
            val bitcoinViewModel = hiltViewModel<BitcoinViewModel>()
            val bitcoinUiState by bitcoinViewModel.uiState.observeAsState(
                BitcoinPriceUiState(false, "")
            )
            BitcoinScreen(
                uiState = bitcoinUiState
            ) { bitcoinViewModel.refreshBitcoinPrice() }
        }
        composable(Screens.ListPage.route) {
            val currenciesUiState by hiltViewModel<CurrenciesViewModel>().uiState.collectAsState()
            ListScreen(currenciesUiState) { }
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
