package com.ing.trading.fx.processingunit.application.market

import com.ing.fx.trading.marketclient.api.model.PublishFailure
import com.ing.fx.trading.marketclient.api.model.PublishResult
import com.ing.fx.trading.marketclient.api.model.PublishSuccess
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.service.MarketChannel
import com.ing.trading.fx.processingunit.domain.QuotesRepository
import java.lang.RuntimeException

class MarketChannelImpl(
        private val quotesRepository: QuotesRepository
) : MarketChannel {
    override fun publish(quote: Quote): PublishResult<Quote> {
        return try {
            quotesRepository.add(quote)
            PublishSuccess(quote)
        } catch (ex: Exception) {
            PublishFailure(RuntimeException(ex))
        }
    }

    override fun publish(quotes: List<Quote>): PublishResult<List<Quote>> {
        return try {
            quotesRepository.addAll(quotes)
            PublishSuccess(quotes)
        } catch (ex: Exception) {
            PublishFailure(RuntimeException(ex))
        }
    }


}