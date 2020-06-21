package com.ing.fx.trading.marketclient.infrastructure.imdg

import com.hazelcast.client.config.ClientConfig
import com.hazelcast.client.config.ClientConnectionStrategyConfig
import com.hazelcast.spring.context.SpringManagedContext
import org.springframework.context.support.beans

val imdgConfiguration = beans {

    bean { SpringManagedContext() }

    bean {
        val springManagedContext = ref<SpringManagedContext>()
        val imdgProperties = ref<IMDGProperties>()
        val clientConfig = ClientConfig()

        clientConfig.managedContext = springManagedContext
        clientConfig.connectionStrategyConfig.reconnectMode = ClientConnectionStrategyConfig.ReconnectMode.ON
        clientConfig.connectionStrategyConfig.isAsyncStart = imdgProperties.asyncStartClient

        clientConfig.connectionStrategyConfig.connectionRetryConfig.initialBackoffMillis = imdgProperties.initialBackoffMillis
        clientConfig.connectionStrategyConfig.connectionRetryConfig.maxBackoffMillis = imdgProperties.maxBackoffMillis
        clientConfig.connectionStrategyConfig.connectionRetryConfig.multiplier = imdgProperties.multiplier
        clientConfig.connectionStrategyConfig.connectionRetryConfig.clusterConnectTimeoutMillis = imdgProperties.clusterConnectTimeoutMillis
        clientConfig.connectionStrategyConfig.connectionRetryConfig.jitter = imdgProperties.jitter

        if (imdgProperties.userCodeDeploymentEnabled) {
            clientConfig.enableUserCodeDeployment()
        }

        clientConfig.configureClientProperties(imdgProperties)

        clientConfig
    }
}

private fun ClientConfig.enableUserCodeDeployment() {
    this.userCodeDeploymentConfig.isEnabled = true

    // TODO: Add task

    classLoader = this.classLoader

}

private fun ClientConfig.configureClientProperties(imdgProperties: IMDGProperties) {
    imdgProperties.clientProperties.forEach {
        this.setProperty(it.key, it.value)
    }
}