package com.ing.fx.trading.marketclient

import com.ing.fx.trading.marketclient.infrastructure.imdg.imdgConfiguration
import com.ing.fx.trading.marketclient.infrastructure.pu.processingUnitConfiguration
import org.springframework.context.ApplicationContextInitializer
import org.springframework.context.support.BeanDefinitionDsl
import org.springframework.context.support.GenericApplicationContext

class MarketClientApplicationBeansInitializer : ApplicationBeansInitializer(marketClientApplicationConfig)

val marketClientApplicationConfig: ApplicationConfig
    get() = ApplicationConfig(
            listOf(
                    imdgConfiguration,
                    processingUnitConfiguration,
                    config
            )
    )

open class ApplicationBeansInitializer(
        private val config: ApplicationConfig
) : ApplicationContextInitializer<GenericApplicationContext> {
    override fun initialize(applicationContext: GenericApplicationContext) {
        config.get().initialize(applicationContext)
    }

    private fun List<BeanDefinitionDsl>.initialize(applicationContext: GenericApplicationContext) {
        this.forEach { it.initialize(applicationContext) }
    }
}

class ApplicationConfig(config: List<BeanDefinitionDsl>) {

    private val config: MutableList<BeanDefinitionDsl> = config.toMutableList()

    fun include(config: BeanDefinitionDsl): ApplicationConfig {
        this.config += config
        return this
    }

    fun exclude(config: BeanDefinitionDsl): ApplicationConfig {
        this.config -= config
        return this
    }

    fun get(): List<BeanDefinitionDsl> {
        return ArrayList(config)
    }
}