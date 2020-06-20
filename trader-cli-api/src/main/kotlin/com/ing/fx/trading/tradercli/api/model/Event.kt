package com.ing.fx.trading.tradercli.api.model

import java.io.Serializable

sealed class Event(
    open val occurredAt: DateTime
) : Serializable

data class BuySucceeded(
    override val occurredAt: DateTime
) : Event(occurredAt), Serializable

data class SellSucceeded(
    override val occurredAt: DateTime
) : Event(occurredAt), Serializable

