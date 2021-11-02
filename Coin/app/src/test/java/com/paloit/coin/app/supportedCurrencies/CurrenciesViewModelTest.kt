package com.paloit.coin.app.supportedCurrencies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.paloit.coin.MainCoroutineRule
import com.paloit.coin.platform.repository.CurrenciesRepository
import com.paloit.coin.platform.repository.data.Currency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CurrenciesViewModelTest {
    private lateinit var viewModel: CurrenciesViewModel
    private val currenciesRepository: CurrenciesRepository = mock()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CurrenciesViewModel(currenciesRepository)
    }

    @Test
    fun `GIVEN the list of supported currencies contains Australia WHEN app fetches a list of supported currencies THEN ui state holds a list of supported currencies with Australia`() {
        mainCoroutineRule.runBlockingTest {
            whenever(currenciesRepository.getSupportedCurrencies()).thenReturn(
                listOf(
                    Currency("Australia", "AUD")
                )
            )
            viewModel.fetchSupportedCurrencies()
            assert(viewModel.uiState.value.itemsList[0].country == "Australia")
        }
    }
}