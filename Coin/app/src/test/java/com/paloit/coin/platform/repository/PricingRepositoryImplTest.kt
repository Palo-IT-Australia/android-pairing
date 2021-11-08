package com.paloit.coin.platform.repository

import com.paloit.coin.core.rules.MainCoroutineRule
import com.paloit.coin.platform.api.BpiApi
import com.paloit.coin.platform.api.data.BpiCurrentPriceResponse
import com.paloit.coin.platform.api.data.BpiPrice
import com.paloit.coin.platform.api.data.BpiTime
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class PricingRepositoryImplTest {
    private lateinit var pricingRepository: PricingRepository

    private val bpiApi: BpiApi = mock()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        pricingRepository = PricingRepositoryImpl(bpiApi)
    }

    @Test
    fun `GIVEN bitcoin price is known WHEN app successfully calls the BpiCurrentPrice endpoint THEN return the Price object`() {
        val bpiPrice = BpiPrice("USD", "Bitcoin", "15.39", 15.39, "USD")
        mainCoroutineRule.runBlockingTest {
            whenever(bpiApi.getBitcoinCurrentPrice()).thenReturn(
                BpiCurrentPriceResponse(
                    mapOf("USD" to bpiPrice),
                    "Bitcoin Chart",
                    "Not a financial advice",
                    BpiTime("123", "123", "123")
                )
            )
            val result = pricingRepository.getBitCoinPrice()
            assert(result.rate == "15.39")
        }
    }
}
