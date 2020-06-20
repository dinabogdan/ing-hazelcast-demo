package com.ing.fx.trading.tradercli.infrastructure.imdg

import com.hazelcast.core.HazelcastInstance
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono
import java.lang.Exception
import java.util.concurrent.CompletableFuture

class HazelcastExecutor(
        private val hazelcastInstance: HazelcastInstance,
        private val imdgProperties: IMDGProperties
) {

    val logger: Logger = LoggerFactory.getLogger(HazelcastExecutor::class.java)

    fun <T> execute(request: HazelcastRequest<T>): Mono<T> {
        val result = CompletableFuture<T>()

        try {
            val executorService = hazelcastInstance.getExecutorService(imdgProperties.executorServiceName)

            executorService.submitToKeyOwner(request.task, request.partitioningKey, CompletableFutureExecutionCallback(result))
        } catch (ex: Exception) {
            result.completeExceptionally(ex)
        }

        return Mono.fromFuture(result)
    }

}