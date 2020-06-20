package com.ing.trading.fx.processingunit.application.trader

import com.ing.fx.trading.tradercli.api.model.BuyCommand
import com.ing.fx.trading.tradercli.api.model.SellCommand
import com.ing.fx.trading.tradercli.api.service.Trader
import com.ing.trading.fx.processingunit.domain.TraderHistoryRepository

class TraderServiceImpl(
        private val traderHistoryRepository: TraderHistoryRepository
) : Trader() {

    override fun buy(command: BuyCommand) {
        traderHistoryRepository.add(command)
    }

    override fun sell(command: SellCommand) {
        traderHistoryRepository.add(command)
    }
}