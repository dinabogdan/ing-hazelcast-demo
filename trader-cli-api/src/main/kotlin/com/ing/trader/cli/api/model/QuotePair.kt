package com.ing.trader.cli.api.model

enum class QuotePair(val symbol: String) {
    EUR_USD("EUR/USD"),
    GBP_USD("GBP/USD"),
    USD_JPY("USD/JPY"),
    USD_CHF("USD/CHF"),
    AUD_USD("AUD/USD"),
    USD_CAD("USD/CAD")
}