package com.ing.trading.fx.processingunit.application.market

import com.ing.fx.trading.marketclient.api.model.PublishFailure
import com.ing.fx.trading.marketclient.api.model.PublishResult
import com.ing.fx.trading.marketclient.api.model.PublishSuccess
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.service.MarketChannel
import com.ing.trading.fx.processingunit.domain.QuotesRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.lang.RuntimeException

class MarketChannelImpl(
        private val quotesRepository: QuotesRepository
) : MarketChannel {

    private val logger: Logger = LoggerFactory.getLogger(MarketChannelImpl::class.java)

    override fun publish(quote: Quote): PublishResult<Quote> {
        return try {
            quotesRepository.add(quote)
            val result = PublishSuccess(quote)
            logger.info("Published $result")
            result
        } catch (ex: Exception) {
            PublishFailure(RuntimeException(ex))
        }
    }

    override fun publish(quotes: List<Quote>): PublishResult<List<Quote>> {
        return try {
            quotesRepository.addAll(quotes)
            val result = PublishSuccess(quotes)
            logger.info("Published $result")
            result
        } catch (ex: Exception) {
            PublishFailure(RuntimeException(ex))
        }
    }
}