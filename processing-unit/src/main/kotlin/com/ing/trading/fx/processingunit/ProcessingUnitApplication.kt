package com.ing.trading.fx.processingunit

import com.ing.trading.fx.processingunit.infrastructure.imdg.IMDGProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableConfigurationProperties(
        IMDGProperties::class
)
class ProcessingUnitApplication

fun main(args: Array<String>) {
    runApplication<ProcessingUnitApplication>(*args)
}
