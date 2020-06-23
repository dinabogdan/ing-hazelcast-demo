package com.ing.fx.trading.marketclient

import com.ing.fx.trading.marketclient.infrastructure.imdg.IMDGProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@EnableConfigurationProperties(
        IMDGProperties::class
)
@SpringBootApplication
class MarketClientApplication

fun main(vararg args: String) {
    runApplication<MarketClientApplication>(*args)
}
