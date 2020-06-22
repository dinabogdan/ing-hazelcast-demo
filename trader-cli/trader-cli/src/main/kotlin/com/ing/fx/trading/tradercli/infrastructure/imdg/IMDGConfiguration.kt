package com.ing.fx.trading.tradercli.infrastructure.imdg

import com.hazelcast.client.config.ClientConfig
import com.hazelcast.client.config.ClientConnectionStrategyConfig
import com.hazelcast.core.HazelcastInstance
import com.hazelcast.spring.context.SpringManagedContext
import com.ing.fx.trading.marketclient.api.model.Quote
import com.ing.fx.trading.marketclient.api.model.QuotePair
import com.ing.fx.trading.tradercli.infrastructure.pu.BuyTask
import com.ing.fx.trading.tradercli.infrastructure.pu.SellTask
import org.springframework.context.support.beans

val imdgConfiguration = beans {

    bean { SpringManagedContext() }

    bean {
        val hazelcastInstance = ref<HazelcastInstance>()
        val map = hazelcastInstance.getReplicatedMap<QuotePair, Quote>("QUOTES_MAP")
        map
    }

    bean { QuotesMapEntryListener() }

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

//        val listenerConfig = ListenerConfig(QuotesMapEntryListener::class.java.name)
////        listenerConfig.className = QuotesMapEntryListener::class.java.name
//        listenerConfig.implementation = QuotesMapEntryListener()
//
//        clientConfig.addListenerConfig(listenerConfig)

        if (imdgProperties.userCodeDeploymentEnabled) {
            clientConfig.enableUserCodeDeployment()
        }

        clientConfig.configureClientProperties(imdgProperties)

        clientConfig
    }

    bean { HazelcastExecutor(ref(), ref()) }
}

private fun ClientConfig.enableUserCodeDeployment() {
    this.userCodeDeploymentConfig.isEnabled = true

    this.userCodeDeploymentConfig.addClass(BuyTask::class.java)
    this.userCodeDeploymentConfig.addClass(BuyTask.Companion::class.java)
    this.userCodeDeploymentConfig.addClass(SellTask::class.java)
    this.userCodeDeploymentConfig.addClass(SellTask.Companion::class.java)
    this.userCodeDeploymentConfig.addClass(QuotesMapEntryListener::class.java)

    classLoader = this.classLoader

}

private fun ClientConfig.configureClientProperties(imdgProperties: IMDGProperties) {
    imdgProperties.clientProperties.forEach {
        this.setProperty(it.key, it.value)
    }
}