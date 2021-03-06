package com.ing.fx.trading.tradercli.api.model

import java.io.Serializable

data class Quote(
    val pair: QuotePair,
    val bid: QuoteBid,
    val ask: QuoteAsk,
    val high: QuoteHigh,
    val low: QuoteLow,
    val change: QuoteChange,
    val time: Time
): Serializable