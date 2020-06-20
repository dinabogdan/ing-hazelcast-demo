package com.ing.trading.fx.processingunit.infrastructure

import com.hazelcast.core.HazelcastInstance
import com.ing.fx.trading.tradercli.api.model.Command
import com.ing.fx.trading.tradercli.api.model.TraderRefId
import com.ing.trading.fx.processingunit.domain.TraderHistoryRepository
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TraderHistoryRepositoryImpl(
        private val hazelcastInstance: HazelcastInstance
) : TraderHistoryRepository {

    private val logger: Logger = LoggerFactory.getLogger(TraderHistoryRepositoryImpl::class.java)

    override fun findAll(trader: TraderRefId): List<Command> {
        logger.info("Retrieving trading history for $trader")
        val traderHistoryMap = hazelcastInstance.getMap<String, Command>("TRADER_HISTORY_MAP")
        return traderHistoryMap.values.toList()
    }

    override fun add(command: Command) {
        logger.info("Add $command to trader's ${command.traderRefId} history")
        val traderHistoryMap = hazelcastInstance.getMap<String, Command>("TRADER_HISTORY_MAP")
        traderHistoryMap.put(command.traderRefId.value, command)
    }
}