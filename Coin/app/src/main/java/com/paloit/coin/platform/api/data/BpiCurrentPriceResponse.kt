package com.paloit.coin.platform.api.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BpiCurrentPriceResponse(
    val bpi: Map<String, BpiPrice>,
    val chartName: String,
    val disclaimer: String,
    val time: BpiTime
)
