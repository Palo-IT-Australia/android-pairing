package com.paloit.coin.platform.api.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BpiCurrenciesResponseItem(
    val country: String,
    val currency: String
)
