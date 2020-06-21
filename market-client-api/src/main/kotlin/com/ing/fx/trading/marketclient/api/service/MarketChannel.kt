package com.ing.fx.trading.marketclient.api.service

import com.ing.fx.trading.marketclient.api.model.PublishResult
import com.ing.fx.trading.marketclient.api.model.Quote

interface MarketChannel {

    fun publish(quotes: List<Quote>): PublishResult<List<Quote>>

    fun publish(quote: Quote): PublishResult<Quote>
}