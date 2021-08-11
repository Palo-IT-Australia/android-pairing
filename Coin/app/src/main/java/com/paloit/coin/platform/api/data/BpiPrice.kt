package com.paloit.coin.platform.api.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BpiPrice(
    val code: String,
    val description: String,
    val rate: String,
    val rate_float: Double,
    val symbol: String
)