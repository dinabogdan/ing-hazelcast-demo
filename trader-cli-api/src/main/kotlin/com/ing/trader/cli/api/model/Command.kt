package com.ing.trader.cli.api.model

sealed class Command(
    open val units: Int,
    open val quote: Quote
)

data class BuyCommand(
    override val units: Int,
    override val quote: Quote
) : Command(units, quote)

data class SellCommand(
    override val units: Int,
    override val quote: Quote
) : Command(units, quote)
