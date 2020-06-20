package com.ing.fx.trading.tradercli.api

import org.springframework.context.support.beans

val routerConfiguration = beans {
    bean { route(ref(), ref()) }
}