package com.ing.trading.fx.processingunit.infrastructure

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.map.IMap
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.model.QuotePair
import com.ing.trading.fx.processingunit.domain.QuotesRepository

class QuotesRepositoryImpl(
        hazelcastInstance: HazelcastInstance
) : QuotesRepository {

    private val map: IMap<QuotePair, Quote> = hazelcastInstance.getMap<QuotePair, Quote>("QUOTES_MAP")

    override fun findAll(): List<Quote> {
        return map.values.toList()
    }

    override fun addAll(quotes: List<Quote>) {
        map.putAll(quotes.map { quote ->
            (quote.pair to quote)
        })
    }

    override fun add(quote: Quote) {
        map[quote.pair] = quote
    }
}