package com.ing.fx.trading.tradercli

import com.ing.fx.trading.tradercli.infrastructure.imdg.IMDGProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
        IMDGProperties::class
)
class TraderCliApplication

fun main(args: Array<String>) {
    runApplication<TraderCliApplication>(*args)
}