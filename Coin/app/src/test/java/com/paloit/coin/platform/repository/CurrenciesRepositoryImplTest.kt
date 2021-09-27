package com.paloit.coin.platform.repository

import com.paloit.coin.MainCoroutineRule
import com.paloit.coin.platform.api.BpiApi
import com.paloit.coin.platform.api.data.BpiCurrenciesResponseItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class CurrenciesRepositoryImplTest {
    private lateinit var currenciesRepository: CurrenciesRepository

    private val bpiApi: BpiApi = mock()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @Before
    fun setUp() {
        currenciesRepository = CurrenciesRepositoryImpl(bpiApi)
    }

    @Test
    fun `GIVEN the api has a currency for Australia WHEN app calls the api THEN return Australian currency`() {
        val currenciesList = listOf(
            BpiCurrenciesResponseItem("Australia", "AUD")
        )
        mainCoroutineRule.runBlockingTest {
            whenever(bpiApi.getSupportedCurrencies()).thenReturn(currenciesList)
            val result = currenciesRepository.getSupportedCurrencies()
            assert(result[0].country == currenciesList[0].country)
        }
    }
}
