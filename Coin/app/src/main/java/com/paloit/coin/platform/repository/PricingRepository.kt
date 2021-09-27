package com.paloit.coin.platform.repository

import com.paloit.coin.platform.api.BpiApi
import com.paloit.coin.platform.mappers.mapToDomain
import com.paloit.coin.platform.repository.data.Price
import javax.inject.Inject

interface PricingRepository {
    suspend fun getBitCoinPrice(): Price
}

class PricingRepositoryImpl @Inject constructor(
    private val bpiApi: BpiApi
) : PricingRepository {
    override suspend fun getBitCoinPrice(): Price = bpiApi.getBitcoinCurrentPrice().mapToDomain()
}
