package com.ing.trading.fx.processingunit.infrastructure

import com.ing.trading.fx.processingunit.application.trader.TraderServiceImpl
import org.springframework.context.support.beans

val processingUnitConfiguration = beans {
    bean { TraderServiceImpl() }
}