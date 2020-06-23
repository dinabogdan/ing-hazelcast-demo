package com.ing.trading.fx.processingunit.infrastructure.imdg

import com.hazelcast.config.*
import com.hazelcast.spring.context.SpringManagedContext
import org.springframework.context.support.beans

val imdgConfiguration = beans {

    bean { SpringManagedContext() }

    bean {
        val springManagedContext = ref<SpringManagedContext>()
        val config = Config()

        config.managedContext = springManagedContext
        config.userCodeDeploymentConfig.isEnabled = true
        config.networkConfig.join.tcpIpConfig.isEnabled = true

        val imdgProperties = ref<IMDGProperties>()
        if (imdgProperties.tcpProperties.enabled) {
            config.networkConfig.join.multicastConfig.isEnabled = false
            config.networkConfig.join.tcpIpConfig.members = imdgProperties.tcpProperties.members
        }

        config.addTraderHistoryMap(ref())
        config.addQuotesMap()

        config
    }
}

private fun Config.addTraderHistoryMap(imdgProperties: IMDGProperties) {
    val traderHistoryMapConfig = MapConfig()
    traderHistoryMapConfig.name = "TRADER_HISTORY_MAP"
    traderHistoryMapConfig.backupCount = 2
    traderHistoryMapConfig.timeToLiveSeconds = 3600
    traderHistoryMapConfig.evictionConfig.evictionPolicy = EvictionPolicy.NONE
    traderHistoryMapConfig.evictionConfig.maxSizePolicy = MaxSizePolicy.PER_NODE
    traderHistoryMapConfig.evictionConfig.size = imdgProperties.maxSize.toInt()

    this.addMapConfig(traderHistoryMapConfig)

}

private fun Config.addQuotesMap() {
    val quotesMapConfig = getReplicatedMapConfig("QUOTES_MAP")
    quotesMapConfig.inMemoryFormat = InMemoryFormat.BINARY
}