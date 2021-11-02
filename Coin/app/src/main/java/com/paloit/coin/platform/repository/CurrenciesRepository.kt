package com.paloit.coin.platform.repository

import com.paloit.coin.di.IoDispatcher
import com.paloit.coin.platform.api.BpiApi
import com.paloit.coin.platform.mappers.mapToDomain
import com.paloit.coin.platform.repository.data.Currency
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface CurrenciesRepository {
    suspend fun getSupportedCurrencies(): List<Currency>
}

class CurrenciesRepositoryImpl @Inject constructor(
    private val bpiApi: BpiApi,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : CurrenciesRepository {
    override suspend fun getSupportedCurrencies(): List<Currency> {
        return withContext(ioDispatcher) {
            return@withContext bpiApi.getSupportedCurrencies().map {
                it.mapToDomain()
            }
        }
    }
}
