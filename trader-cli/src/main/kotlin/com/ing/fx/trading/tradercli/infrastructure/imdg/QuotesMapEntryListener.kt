package com.ing.fx.trading.tradercli.infrastructure.imdg

import com.hazelcast.core.EntryEvent
import com.hazelcast.core.EntryListener
import com.hazelcast.map.MapEvent
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.model.QuotePair
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.io.Serializable

class QuotesMapEntryListener : EntryListener<QuotePair, Quote>, Serializable {

    private val logger: Logger = LoggerFactory.getLogger(QuotesMapEntryListener::class.java)

    override fun entryAdded(event: EntryEvent<QuotePair, Quote>?) {
        logger.info("A new entry ${event?.value} was added")
    }

    override fun entryExpired(event: EntryEvent<QuotePair, Quote>?) {
        TODO("Not yet implemented")
    }

    override fun entryEvicted(event: EntryEvent<QuotePair, Quote>?) {
        TODO("Not yet implemented")
    }

    override fun entryUpdated(event: EntryEvent<QuotePair, Quote>?) {
        logger.info("An entry for ${event?.key} was updated with ${event?.value}")
    }

    override fun mapCleared(event: MapEvent?) {
        TODO("Not yet implemented")
    }

    override fun mapEvicted(event: MapEvent?) {
        TODO("Not yet implemented")
    }

    override fun entryRemoved(event: EntryEvent<QuotePair, Quote>?) {
        TODO("Not yet implemented")
    }
}