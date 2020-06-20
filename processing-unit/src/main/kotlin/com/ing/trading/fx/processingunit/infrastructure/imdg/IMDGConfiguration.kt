package com.ing.trading.fx.processingunit.infrastructure.imdg

import com.hazelcast.config.Config
import com.hazelcast.spring.context.SpringManagedContext
import org.springframework.context.support.beans

val imdgConfiguration = beans {

    bean { SpringManagedContext() }

    bean {
        val springManagedContext = ref<SpringManagedContext>()
        val config = Config()

        config.managedContext = springManagedContext
        config.userCodeDeploymentConfig.isEnabled = true

        config
    }
}