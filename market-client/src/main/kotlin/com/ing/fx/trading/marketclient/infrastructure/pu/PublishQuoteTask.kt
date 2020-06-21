package com.ing.fx.trading.marketclient.infrastructure.pu

import com.hazelcast.spring.context.SpringAware
import com.ing.fx.trading.marketclient.api.model.PublishResult
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.service.MarketChannel
import org.springframework.beans.factory.annotation.Autowired
import java.io.Serializable
import java.util.concurrent.Callable

@SpringAware
class PublishQuoteTask(
        private val quote: Quote
) : Callable<PublishResult<Quote>>, Serializable {

    companion object {
        private const val serialVersionUID = -32133126719161714L
    }

    @Autowired
    @Transient
    lateinit var marketChannel: MarketChannel

    override fun call(): PublishResult<Quote> {
        return marketChannel.publish(quote)
    }
}