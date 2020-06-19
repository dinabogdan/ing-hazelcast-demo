package com.ing.fx.trading.marketclient.api.service

import com.ing.fx.trading.marketclient.api.model.Quote

interface MarketClientService {

    fun publish(quotes: List<Quote>)
}