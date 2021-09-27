package com.paloit.coin.app.bitcoinPrice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paloit.coin.app.bitcoinPrice.BitcoinPriceUiState
import com.paloit.coin.di.IoDispatcher
import com.paloit.coin.platform.repository.PricingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class BitcoinViewModel @Inject constructor(
    private val pricingRepository: PricingRepository,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableLiveData<BitcoinPriceUiState>()
    val uiState: LiveData<BitcoinPriceUiState> = _uiState

    init {
        refreshBitcoinPrice()
    }

    fun refreshBitcoinPrice() {
        viewModelScope.launch {
            _uiState.postValue(BitcoinPriceUiState(true, _uiState.value?.bitcoinPrice ?: ""))
            withContext(ioDispatcher) {
                val price = pricingRepository.getBitCoinPrice()
                _uiState.postValue(BitcoinPriceUiState(false, price.rate))
            }
        }
    }
}
