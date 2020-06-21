package com.ing.fx.trading.marketclient.api.model

import java.io.Serializable
import java.lang.RuntimeException

sealed class PublishResult<out T> : Serializable

class PublishFailure<out T>(
    val exception: RuntimeException
) : PublishResult<T>(), Serializable

class PublishSuccess<out T>(
    val value: T
) : PublishResult<T>(), Serializable