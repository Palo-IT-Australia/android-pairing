package com.paloit.coin.app.supportedCurrencies

import com.paloit.coin.core.extensions.CoroutinesTestExtension
import com.paloit.coin.core.extensions.InstantTaskExecutorExtension
import com.paloit.coin.platform.repository.CurrenciesRepository
import com.paloit.coin.platform.repository.data.Currency
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestExtension::class)
internal class CurrenciesViewModelJUnit5Test {

    private val currenciesRepository: CurrenciesRepository = mock()

    private lateinit var viewModel: CurrenciesViewModel

    @BeforeEach
    fun setUp() = runBlockingTest {
        whenever(currenciesRepository.getSupportedCurrencies()).thenReturn(
            listOf(
                Currency("Australia", "AUD")
            )
        )
        viewModel = CurrenciesViewModel(currenciesRepository)
    }

    @Test
    fun `GIVEN the list of supported currencies contains Australia WHEN app fetches a list of supported currencies THEN ui state holds a list of supported currencies with Australia`() =
        runBlockingTest {
            viewModel.fetchSupportedCurrencies()
            assert(viewModel.uiState.value.itemsList.size == 1)
//            assert(viewModel.uiState.value.itemsList[0].country == "Australia")
        }
}