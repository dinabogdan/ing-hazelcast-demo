package com.ing.fx.trading.marketclient.infrastructure.pu

import com.ing.fx.trading.marketclient.api.model.PublishResult
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.application.MarketChannelPublisher
import com.ing.fx.trading.marketclient.infrastructure.imdg.HazelcastExecutor
import com.ing.fx.trading.marketclient.infrastructure.imdg.HazelcastRequest
import reactor.core.publisher.Mono

class MarketChannelPublisherImpl(
        private val hazelcastExecutor: HazelcastExecutor
) : MarketChannelPublisher {

    override fun publish(quotes: List<Quote>): Mono<PublishResult<List<Quote>>> {
        return hazelcastExecutor.execute(
                HazelcastRequest(task = PublishQuotesTask(quotes))
        )
    }


    override fun publish(quote: Quote): Mono<PublishResult<Quote>> {
        return hazelcastExecutor.execute(
                HazelcastRequest(task = PublishQuoteTask(quote))
        )
    }
}