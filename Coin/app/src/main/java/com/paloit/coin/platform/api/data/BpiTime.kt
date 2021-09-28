package com.paloit.coin.platform.api.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BpiTime(
    val updated: String,
    val updatedISO: String,
    val updateduk: String
)
