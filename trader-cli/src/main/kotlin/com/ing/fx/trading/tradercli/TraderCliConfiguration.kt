package com.ing.fx.trading.tradercli

import com.ing.fx.trading.tradercli.application.channel.model.TraderId
import org.springframework.context.support.beans
import java.util.*

val traderCliConfiguration = beans {

    bean { TraderId(UUID.randomUUID().toString()) }

    bean { TraderCliApplicationCommandLineRunner(ref(), ref()) }
}