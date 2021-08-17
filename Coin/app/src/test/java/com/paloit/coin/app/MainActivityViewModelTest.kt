package com.paloit.coin.app

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.paloit.coin.MainCoroutineRule
import com.paloit.coin.platform.repository.PricingRepository
import com.paloit.coin.platform.repository.data.Price
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class MainActivityViewModelTest {
    private val testDispatcher = TestCoroutineDispatcher()

    private lateinit var viewModel: MainActivityViewModel
    private lateinit var pricingRepository: PricingRepository

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        pricingRepository = mock()
        testDispatcher.runBlockingTest {
            whenever(pricingRepository.getBitCoinPrice()).thenReturn(Price("12.56"))
        }
        viewModel = MainActivityViewModel(pricingRepository, testDispatcher)
        viewModel.uiState.observeForever { }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `GIVEN crypto currency has a set price WHEN app retrieves the price THEN return price object with price and false loading state`() {
        testDispatcher.runBlockingTest {
            viewModel.refreshBitcoinPrice()
        }
        assert(viewModel.uiState.value == MainActivityUiState(false, "12.56"))
    }
}
