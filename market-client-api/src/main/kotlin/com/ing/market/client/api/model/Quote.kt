package com.ing.market.client.api.model

data class Quote(
    val pair: QuotePair,
    val bid: QuoteBid,
    val ask: QuoteAsk,
    val high: QuoteHigh,
    val low: QuoteLow,
    val change: QuoteChange,
    val time: QuoteTime
)