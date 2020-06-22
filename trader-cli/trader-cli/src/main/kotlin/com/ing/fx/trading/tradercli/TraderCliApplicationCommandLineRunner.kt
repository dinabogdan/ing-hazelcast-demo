package com.ing.fx.trading.tradercli

import com.hazelcast.replicatedmap.ReplicatedMap
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.model.QuotePair
import com.ing.fx.trading.tradercli.infrastructure.imdg.QuotesMapEntryListener
import org.springframework.boot.CommandLineRunner

class TraderCliApplicationCommandLineRunner(
        private val quotesMap: ReplicatedMap<QuotePair, Quote>,
        private val quotesMapEntryListener: QuotesMapEntryListener
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        quotesMap.addEntryListener(quotesMapEntryListener)
    }
}