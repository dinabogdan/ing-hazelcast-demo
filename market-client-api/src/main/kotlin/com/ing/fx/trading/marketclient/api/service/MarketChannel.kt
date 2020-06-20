package com.ing.fx.trading.marketclient.api.service

import com.ing.fx.trading.marketclient.api.model.Quote

interface MarketChannel {

    fun publish(quotes: List<Quote>)
}