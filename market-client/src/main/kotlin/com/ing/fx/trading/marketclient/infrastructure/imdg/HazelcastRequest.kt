package com.ing.fx.trading.tradercli.infrastructure.imdg

import java.util.concurrent.Callable

data class HazelcastRequest<T>(
        val partitioningKey: String? = null,
        val task: Callable<T>
)