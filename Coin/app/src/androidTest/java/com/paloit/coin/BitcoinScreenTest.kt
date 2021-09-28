package com.paloit.coin

import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import com.paloit.coin.app.bitcoinPrice.BitcoinScreen
import com.paloit.coin.app.bitcoinPrice.BitcoinPriceUiState
import org.junit.Rule
import org.junit.Test

class BitcoinScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun given_bitcoin_price_is_known_when_app_retrieves_bitcoin_price_then_show_bitcoin_price() {
        val mainActivityUiState = BitcoinPriceUiState(false, "12.59")
        composeTestRule.setContent {
            BitcoinScreen(uiState = mainActivityUiState) {}
        }

        composeTestRule.onNode(
            hasText("12.59"),
            useUnmergedTree = true
        ).assertExists()
    }
}
