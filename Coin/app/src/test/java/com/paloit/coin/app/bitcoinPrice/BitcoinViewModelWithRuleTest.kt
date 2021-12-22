package com.paloit.coin.app.bitcoinPrice

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.paloit.coin.core.rules.MainCoroutineRule
import com.paloit.coin.platform.repository.PricingRepository
import com.paloit.coin.platform.repository.data.Price
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class BitcoinViewModelWithRuleTest {
    private lateinit var viewModel: BitcoinViewModel
    private val pricingRepository: PricingRepository = mock()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        runTest {
            whenever(pricingRepository.getBitCoinPrice()).thenReturn(Price("12.56"))
        }
        viewModel = BitcoinViewModel(pricingRepository, Dispatchers.Main)
    }

    @Test
    fun `GIVEN crypto currency has a set price WHEN app retrieves the price THEN return price object with price and false loading state`() {
        runTest {
            viewModel.refreshBitcoinPrice()
        }
        assert(viewModel.uiState.value == BitcoinPriceUiState(false, "12.56"))
    }
}