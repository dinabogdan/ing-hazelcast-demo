package com.ing.fx.trading.marketclient.lang

import java.math.BigDecimal

fun random(range: String): BigDecimal {
    val max = BigDecimal(range)
    val randomFromDouble = BigDecimal(Math.random())
    return randomFromDouble.divide(max, BigDecimal.ROUND_DOWN)
}