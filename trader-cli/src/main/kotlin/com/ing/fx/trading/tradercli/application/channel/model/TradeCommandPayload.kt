package com.ing.fx.trading.tradercli.application.channel.model

data class TradeCommandPayload(
        val units: Int,
        val quoteItem: QuoteItem
)