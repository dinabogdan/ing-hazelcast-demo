package com.ing.fx.trading.tradercli.api.model

import java.io.Serializable

sealed class Command(
    open val traderRefId: TraderRefId,
    open val units: Int,
    open val quotePair: QuotePair
) : Serializable

data class BuyCommand(
    override val traderRefId: TraderRefId,
    override val units: Int,
    override val quotePair: QuotePair
) : Command(traderRefId, units, quotePair), Serializable

data class SellCommand(
    override val traderRefId: TraderRefId,
    override val units: Int,
    override val quotePair: QuotePair
) : Command(traderRefId, units, quotePair), Serializable
