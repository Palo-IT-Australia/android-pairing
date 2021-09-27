package com.paloit.coin.platform.repository

import com.paloit.coin.platform.api.BpiApi
import com.paloit.coin.platform.mappers.mapToDomain
import com.paloit.coin.platform.repository.data.Currency
import javax.inject.Inject

interface CurrenciesRepository {
    suspend fun getSupportedCurrencies(): List<Currency>
}

class CurrenciesRepositoryImpl @Inject constructor(
    private val bpiApi: BpiApi
) : CurrenciesRepository {
    override suspend fun getSupportedCurrencies(): List<Currency> =
        bpiApi.getSupportedCurrencies().map {
            it.mapToDomain()
        }
}