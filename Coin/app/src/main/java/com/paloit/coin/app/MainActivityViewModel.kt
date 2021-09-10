package com.paloit.coin.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paloit.coin.di.IoDispatcher
import com.paloit.coin.platform.repository.PricingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val pricingRepository: PricingRepository,
    @IoDispatcher private val iODispatcher: CoroutineDispatcher
) : ViewModel() {
    private val _uiState = MutableLiveData<MainActivityUiState>()
    val uiState: LiveData<MainActivityUiState> = _uiState

    init {
        refreshBitcoinPrice()
    }

    fun refreshBitcoinPrice() {
        viewModelScope.launch {
            _uiState.postValue(MainActivityUiState(true, _uiState.value?.bitcoinPrice ?: ""))
            withContext(iODispatcher) {
                val price = pricingRepository.getBitCoinPrice()
                _uiState.postValue(MainActivityUiState(false, price.rate))
            }
        }
    }
}
