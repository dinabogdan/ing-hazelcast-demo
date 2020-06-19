package com.ing.trader.cli.api.service

import com.ing.trader.cli.api.model.BuyCommand
import com.ing.trader.cli.api.model.SellCommand

interface TraderService {

    fun buy(command: BuyCommand)

    fun sell(command: SellCommand)

}