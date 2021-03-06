package com.ing.fx.trading.tradercli.infrastructure.pu

import com.ing.fx.trading.tradercli.api.model.*
import com.ing.fx.trading.tradercli.application.channel.TradingChannel
import com.ing.fx.trading.tradercli.application.channel.model.TradeCommand
import com.ing.fx.trading.tradercli.application.channel.model.TraderId
import com.ing.fx.trading.tradercli.infrastructure.imdg.HazelcastExecutor
import com.ing.fx.trading.tradercli.infrastructure.imdg.HazelcastRequest
import reactor.core.publisher.Mono

class TradingChannelImpl(
        private val hazelcastExecutor: HazelcastExecutor
) : TradingChannel {
    override fun buy(trader: TraderId, buyCommand: Mono<TradeCommand.BuyCommand>): Mono<BuySucceeded> {
        return buyCommand.flatMap { command ->
            hazelcastExecutor.execute(command.toHazelcastRequest(trader))
        }
    }

    override fun sell(trader: TraderId, sellCommand: Mono<TradeCommand.SellCommand>): Mono<SellSucceeded> {
        return sellCommand.flatMap { command ->
            hazelcastExecutor.execute(command.toHazelcastRequest(trader))
        }
    }
}

private fun TradeCommand.BuyCommand.toHazelcastRequest(trader: TraderId): HazelcastRequest<BuySucceeded> {
    return HazelcastRequest(
            trader.value,
            BuyTask(
                    BuyCommand(
                            traderRefId = TraderRefId(trader.value),
                            units = this.payload.units,
                            quotePair = QuotePair.valueOf(this.payload.quoteItem.name)
                    )
            )
    )
}

private fun TradeCommand.SellCommand.toHazelcastRequest(trader: TraderId): HazelcastRequest<SellSucceeded> {
    return HazelcastRequest(
            trader.value,
            SellTask(
                    SellCommand(
                            traderRefId = TraderRefId(trader.value),
                            units = this.payload.units,
                            quotePair = QuotePair.valueOf(this.payload.quoteItem.name)
                    )
            )
    )
}