package com.ing.fx.trading.marketclient.api.model

import java.io.Serializable
import java.lang.Exception
import java.lang.RuntimeException

sealed class PublishResult<out T> : Serializable {
    abstract fun <B> map(f: (T) -> B): PublishResult<B>

    abstract fun <B> flatMap(f: (T) -> PublishResult<B>): PublishResult<B>
}

class PublishFailure<out T>(
    private val exception: RuntimeException
) : PublishResult<T>(), Serializable {
    override fun <B> map(f: (T) -> B): PublishResult<B> = PublishFailure(exception)

    override fun <B> flatMap(f: (T) -> PublishResult<B>): PublishResult<B> = PublishFailure(exception)

    override fun toString(): String = "PublishFailure(${exception.message})"

}

class PublishSuccess<out T>(
    private val value: T
) : PublishResult<T>(), Serializable {

    override fun <B> map(f: (T) -> B): PublishResult<B> = try {
        PublishSuccess(f(value))
    } catch (ex: RuntimeException) {
        PublishFailure(ex)
    } catch (ex: Exception) {
        PublishFailure(RuntimeException(ex))
    }

    override fun <B> flatMap(f: (T) -> PublishResult<B>): PublishResult<B> = try {
        f(value)
    } catch (ex: RuntimeException) {
        PublishFailure(ex)
    } catch (ex: Exception) {
        PublishFailure(RuntimeException(ex))
    }

    override fun toString(): String = "PublishSuccess(${value})"
}