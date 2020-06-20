package com.ing.fx.trading.tradercli.api.model

sealed class Command(
    open val traderRefId: TraderRefId,
    open val units: Int,
    open val quote: Quote
)

data class BuyCommand(
    override val traderRefId: TraderRefId,
    override val units: Int,
    override val quote: Quote
) : Command(traderRefId, units, quote)

data class SellCommand(
    override val traderRefId: TraderRefId,
    override val units: Int,
    override val quote: Quote
) : Command(traderRefId, units, quote)
