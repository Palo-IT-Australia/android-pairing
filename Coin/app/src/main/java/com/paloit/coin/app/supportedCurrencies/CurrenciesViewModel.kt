package com.paloit.coin.app.supportedCurrencies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.paloit.coin.platform.repository.CurrenciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val currenciesRepository: CurrenciesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(CurrenciesUiState(false, listOf()))
    val uiState: StateFlow<CurrenciesUiState> = _uiState

    fun fetchSupportedCurrencies() {
        viewModelScope.launch {
            _uiState.value = CurrenciesUiState(true, uiState.value.itemsList)
            val result = currenciesRepository.getSupportedCurrencies()
            _uiState.value = CurrenciesUiState(false, result)
        }
    }
}
