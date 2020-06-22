package com.ing.trading.fx.processingunit.infrastructure

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.replicatedmap.ReplicatedMap
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.model.QuotePair
import com.ing.trading.fx.processingunit.domain.QuotesRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class QuotesRepositoryImpl(
        hazelcastInstance: HazelcastInstance
) : QuotesRepository {
    private val logger: Logger = LoggerFactory.getLogger(QuotesRepositoryImpl::class.java)

    private val map: ReplicatedMap<QuotePair, Quote> = hazelcastInstance.getReplicatedMap<QuotePair, Quote>("QUOTES_MAP")

    override fun findAll(): List<Quote> {
        return map.values.toList()
    }

    override fun addAll(quotes: List<Quote>) {
        map.putAll(quotes.map { quote ->
            (quote.pair to quote)
        })
        logger.info("Added $quotes")
    }

    override fun add(quote: Quote) {
        map[quote.pair] = quote
        logger.info("Added $quote")
    }
}