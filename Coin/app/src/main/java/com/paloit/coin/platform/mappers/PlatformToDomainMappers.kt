package com.paloit.coin.platform.mappers

import com.paloit.coin.platform.api.data.BpiCurrentPriceResponse
import com.paloit.coin.platform.repository.data.Price

fun BpiCurrentPriceResponse.mapToDomain(): Price {
    val price = bpi["USD"]
    return if (price != null) {
        Price(price.rate)
    } else {
        Price("")
    }
}
