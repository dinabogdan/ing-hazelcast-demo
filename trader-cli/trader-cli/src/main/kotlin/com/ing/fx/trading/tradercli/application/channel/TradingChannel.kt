package com.ing.fx.trading.tradercli.application.channel

import com.ing.fx.trading.tradercli.application.channel.model.TradeCommand
import com.ing.fx.trading.tradercli.application.channel.model.TraderId
import reactor.core.publisher.Mono

interface TradingChannel {

    fun buy(trader: TraderId, buyCommand: Mono<TradeCommand.BuyCommand>): Mono<Unit>

    fun sell(trader: TraderId, sellCommand: Mono<TradeCommand.SellCommand>): Mono<Unit>
}