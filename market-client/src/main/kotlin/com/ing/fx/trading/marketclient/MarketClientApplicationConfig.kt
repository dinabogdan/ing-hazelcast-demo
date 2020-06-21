package com.ing.fx.trading.marketclient

import org.springframework.context.support.beans

val config = beans {
    bean { MarketClientCommandLineRunner(ref()) }
}