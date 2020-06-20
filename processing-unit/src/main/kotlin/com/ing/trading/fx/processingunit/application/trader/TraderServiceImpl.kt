package com.ing.trading.fx.processingunit.application.trader

import com.ing.fx.trading.tradercli.api.model.*
import com.ing.fx.trading.tradercli.api.service.Trader
import com.ing.trading.fx.processingunit.domain.TraderHistoryRepository
import java.time.LocalDateTime

class TraderServiceImpl(
        private val traderHistoryRepository: TraderHistoryRepository
) : Trader() {

    override fun buy(command: BuyCommand): BuySucceeded {
        traderHistoryRepository.add(command)
        return BuySucceeded(DateTime(LocalDateTime.now()))
    }

    override fun sell(command: SellCommand): SellSucceeded {
        traderHistoryRepository.add(command)
        return SellSucceeded(DateTime(LocalDateTime.now()))
    }
}