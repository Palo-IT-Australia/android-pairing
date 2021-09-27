package com.paloit.coin.app

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.ui.graphics.vector.ImageVector
import com.paloit.coin.R

sealed class Routes(
    val route: String
) {
    object BitcoinRoute :
        Routes(
            "bitcoin"
        )

    object CurrenciesRoute :
        Routes(
            "list"
        )

    object BitcoinCurrencyRoute :
        Routes(
            "bitcoinCurrency"
        )
}

sealed class Screens(
    val route: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    @StringRes val iconDescription: Int
) {
    object BitcoinPage :
        Screens(
            Routes.BitcoinRoute.route,
            R.string.nav_bottom_bar_home,
            Icons.Default.Home,
            R.string.nav_bottom_bar_home
        )

    object ListPage :
        Screens(
            Routes.CurrenciesRoute.route,
            R.string.nav_bottom_bar_list,
            Icons.Default.List,
            R.string.nav_bottom_bar_list
        )
}
