package com.ing.fx.trading.marketclient.lang

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

fun random(range: String): BigDecimal {
    val max = BigDecimal(range)
    val randomFromDouble = BigDecimal(Math.random())
    val actualRandomValue = randomFromDouble.divide(max, BigDecimal.ROUND_DOWN)
    return actualRandomValue.round(MathContext(4, RoundingMode.DOWN))
}