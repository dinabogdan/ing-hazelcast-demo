package com.ing.trading.fx.processingunit.application.market

import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.service.MarketChannel
import com.ing.trading.fx.processingunit.domain.QuotesRepository

class MarketChannelImpl(
        private val quotesRepository: QuotesRepository
) : MarketChannel {

    override fun publish(quotes: List<Quote>) {
        quotesRepository.addAll(quotes)
    }
}