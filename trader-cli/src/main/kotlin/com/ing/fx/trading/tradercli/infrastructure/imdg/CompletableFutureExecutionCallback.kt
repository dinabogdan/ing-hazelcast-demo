package com.ing.fx.trading.tradercli.infrastructure.imdg

import com.hazelcast.core.ExecutionCallback
import reactor.core.publisher.MonoSink
import java.util.concurrent.CompletableFuture

class MonoExecutionCallback<T>(
        val monoSink: MonoSink<T>
) : ExecutionCallback<T> {
    override fun onFailure(t: Throwable) {
        monoSink.error(t)
    }

    override fun onResponse(response: T) {
        monoSink.success(response)
    }
}

class CompletableFutureExecutionCallback<T>(
        private val completableFuture: CompletableFuture<T>
) : ExecutionCallback<T> {

    override fun onFailure(error: Throwable?) {
        completableFuture.completeExceptionally(error)
    }

    override fun onResponse(response: T) {
        completableFuture.complete(response)
    }
}