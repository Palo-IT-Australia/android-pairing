package com.paloit.coin.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.paloit.coin.ui.theme.CoinTheme
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalMaterialApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val items = listOf(Screens.BitcoinPage, Screens.ListPage)

        setContent {
            val viewModel: MainActivityViewModel = viewModel()
            val navController = rememberNavController()
            val uiState by viewModel.uiState.observeAsState(MainActivityUiState(false, ""))

            CoinTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(bottomBar = {
                    AppBottomNavBar(items = items, navController)
                }) {
                    AppNavHost(navController = navController, viewModel, uiState)
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
fun AppNavHost(
    navController: NavHostController,
    viewModel: MainActivityViewModel,
    uiState: MainActivityUiState
) {
    NavHost(navController = navController, startDestination = Screens.BitcoinPage.route) {
        composable(Screens.BitcoinPage.route) {
            BitcoinScreen(
                uiState = uiState
            ) { viewModel.refreshBitcoinPrice() }
        }
        composable(Screens.ListPage.route) { ListScreen() }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CoinTheme {
        BitcoinPrice(true, "10,000,000") {}
    }
}
