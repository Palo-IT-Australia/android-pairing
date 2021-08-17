package com.paloit.coin.platform.api

import com.paloit.coin.platform.api.data.BpiCurrenciesResponseItem
import com.paloit.coin.platform.api.data.BpiCurrentPriceResponse
import retrofit2.http.GET

interface BpiApi {
    companion object {
        // https://www.coindesk.com/coindesk-api
        const val END_POINT = "https://api.coindesk.com/v1/bpi/"
    }

    @GET("currentprice.json")
    suspend fun getBitcoinCurrentPrice(): BpiCurrentPriceResponse

    @GET("supported-currencies.json")
    suspend fun getSupportedCurrencies(): List<BpiCurrenciesResponseItem>

    @GET("currentprice/{code}.json")
    suspend fun getBitcoinCurrentPriceInCurrency(code: String): BpiCurrentPriceResponse
}
