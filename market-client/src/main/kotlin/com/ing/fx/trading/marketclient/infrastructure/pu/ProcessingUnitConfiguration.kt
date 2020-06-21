package com.ing.fx.trading.marketclient.infrastructure.pu

import org.springframework.context.support.beans

val processingUnitConfiguration = beans {
    bean { MarketChannelPublisherImpl((ref())) }
}