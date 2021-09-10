package com.paloit.coin.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.paloit.coin.MainCoroutineRule
import com.paloit.coin.platform.repository.PricingRepository
import com.paloit.coin.platform.repository.data.Price
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainActivityViewModelWithRuleTest {
    private lateinit var viewModel: MainActivityViewModel
    private val pricingRepository: PricingRepository = mock()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        mainCoroutineRule.runBlockingTest {
            whenever(pricingRepository.getBitCoinPrice()).thenReturn(Price("12.56"))
        }
        viewModel = MainActivityViewModel(pricingRepository, Dispatchers.Main)
        viewModel.uiState.observeForever { }
    }

    @Test
    fun `GIVEN crypto currency has a set price WHEN app retrieves the price THEN return price object with price and false loading state`() {
        mainCoroutineRule.runBlockingTest {
            viewModel.refreshBitcoinPrice()
        }
        assert(viewModel.uiState.value == MainActivityUiState(false, "12.56"))
    }
}