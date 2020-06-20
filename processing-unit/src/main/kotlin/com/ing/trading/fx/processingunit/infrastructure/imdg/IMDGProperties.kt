package com.ing.trading.fx.processingunit.infrastructure.imdg

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "hazelcast")
data class IMDGProperties(
        val monitoring: Boolean,
        val maxSize: Long
)