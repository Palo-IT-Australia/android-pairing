package com.paloit.coin.app.bitcoinPrice

import com.paloit.coin.core.extensions.CoroutinesTestExtension
import com.paloit.coin.core.extensions.InstantTaskExecutorExtension
import com.paloit.coin.platform.repository.PricingRepository
import com.paloit.coin.platform.repository.data.Price
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
@ExtendWith(InstantTaskExecutorExtension::class, CoroutinesTestExtension::class)
internal class BitcoinViewModelJUnit5Test {
    private val dispatcher = CoroutinesTestExtension()

    private val pricingRepository: PricingRepository = mock()

    private lateinit var viewModel: BitcoinViewModel

    @BeforeEach
    fun setUp() = dispatcher.runBlockingTest {
        whenever(pricingRepository.getBitCoinPrice()).thenReturn(Price("12.56"))
        viewModel = BitcoinViewModel(pricingRepository, Dispatchers.Main)
    }

    @Test
    fun `GIVEN crypto currency has a set price WHEN app retrieves the price THEN return price object with price and false loading state`() =
        dispatcher.runBlockingTest {
            viewModel.refreshBitcoinPrice()
            assert(viewModel.uiState.value == BitcoinPriceUiState(false, "12.56"))
        }
}
