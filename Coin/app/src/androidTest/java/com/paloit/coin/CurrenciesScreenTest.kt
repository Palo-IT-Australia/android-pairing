package com.paloit.coin

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasAnyChild
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import com.paloit.coin.app.supportedCurrencies.CurrenciesUiState
import com.paloit.coin.app.supportedCurrencies.ListScreen
import com.paloit.coin.platform.repository.data.Currency
import org.junit.Rule
import org.junit.Test

class CurrenciesScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @ExperimentalMaterialApi
    @Test
    fun given_app_on_currencies_screen_when_currencies_retrieved_successfully_then_return_list_of_currencies() {
        val uiState = CurrenciesUiState(
            false, listOf(
                Currency("Australia", "AUD")
            )
        )

        composeTestRule.setContent {
            ListScreen(uiState = uiState) { }
        }

        composeTestRule.onAllNodes(hasAnyChild(hasText("Australia")), useUnmergedTree = true)
            .assertCountEquals(1)
    }
}
