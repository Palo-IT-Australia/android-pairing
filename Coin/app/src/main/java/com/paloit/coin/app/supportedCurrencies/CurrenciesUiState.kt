package com.paloit.coin.app.supportedCurrencies

import com.paloit.coin.platform.repository.data.Currency

data class CurrenciesUiState(
    val isListLoading: Boolean,
    val itemsList: List<Currency>
)
