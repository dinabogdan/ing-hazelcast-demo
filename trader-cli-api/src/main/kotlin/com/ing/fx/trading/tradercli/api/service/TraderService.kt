package com.ing.fx.trading.tradercli.api.service

import com.ing.fx.trading.tradercli.api.model.BuyCommand
import com.ing.fx.trading.tradercli.api.model.SellCommand

abstract class Trader {

    abstract fun buy(command: BuyCommand)

    abstract fun sell(command: SellCommand)

}