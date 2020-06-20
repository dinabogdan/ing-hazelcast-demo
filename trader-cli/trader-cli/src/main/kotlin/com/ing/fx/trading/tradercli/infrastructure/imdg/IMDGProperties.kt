package com.ing.fx.trading.tradercli.infrastructure.imdg

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "hazelcast")
data class IMDGProperties(
        val connectionAttemptLimit: Int,
        val connectionAttemptPeriodMillis: Int,
        val asyncStartClient: Boolean,
        val userCodeDeploymentEnabled: Boolean,
        val clientProperties: Map<String, String>,
        val tcp: TcpProperties?,
        val executorServiceName: String
)

data class TcpProperties(
        val enabled: Boolean = false,
        val members: List<String> = emptyList()
)

fun TcpProperties?.isEnabled(): Boolean = this?.enabled == true