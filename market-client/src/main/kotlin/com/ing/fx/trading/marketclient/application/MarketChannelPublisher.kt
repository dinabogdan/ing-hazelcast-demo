package com.ing.fx.trading.marketclient.application

import com.ing.fx.trading.marketclient.api.model.PublishResult
import com.ing.fx.trading.marketclient.api.model.Quote
import reactor.core.publisher.Mono

interface MarketChannelPublisher {

    fun publish(quotes: List<Quote>): Mono<PublishResult<List<Quote>>>

    fun publish(quote: Quote): Mono<PublishResult<Quote>>
}