package com.ing.fx.trading.tradercli.application.channel.model

sealed class TradeCommand(
        open val payload: TradeCommandPayload
) {
    data class BuyCommand(
            override val payload: TradeCommandPayload
    ) : TradeCommand(payload)

    data class SellCommand(
            override val payload: TradeCommandPayload
    ) : TradeCommand(payload)
}

