package com.ing.trading.fx.processingunit.domain

import com.ing.fx.trading.tradercli.api.model.Command
import com.ing.fx.trading.tradercli.api.model.TraderRefId

interface TraderHistoryRepository {

    fun findAll(trader: TraderRefId): List<Command>

    fun add(command: Command)

}