package com.ing.fx.trading.tradercli.api.service

import com.ing.fx.trading.tradercli.api.model.BuyCommand
import com.ing.fx.trading.tradercli.api.model.SellCommand

interface TraderService {

    fun buy(command: BuyCommand)

    fun sell(command: SellCommand)

}