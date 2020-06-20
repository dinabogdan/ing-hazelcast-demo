package com.ing.fx.trading.tradercli.api.service

import com.ing.fx.trading.tradercli.api.model.BuyCommand
import com.ing.fx.trading.tradercli.api.model.BuySucceeded
import com.ing.fx.trading.tradercli.api.model.SellCommand
import com.ing.fx.trading.tradercli.api.model.SellSucceeded

abstract class Trader {

    abstract fun buy(command: BuyCommand): BuySucceeded

    abstract fun sell(command: SellCommand): SellSucceeded

}