package com.ing.fx.trading.marketclient.infrastructure.imdg

import com.hazelcast.core.ExecutionCallback
import java.util.concurrent.CompletableFuture

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