package com.ing.fx.trading.tradercli.infrastructure.pu

import org.springframework.context.support.beans

val processingUnitConfiguration = beans {
    bean { TradingChannelImpl(ref()) }
}
