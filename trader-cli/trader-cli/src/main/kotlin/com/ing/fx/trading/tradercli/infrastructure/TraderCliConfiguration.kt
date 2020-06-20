package com.ing.fx.trading.tradercli.infrastructure

import com.ing.fx.trading.tradercli.api.model.TraderRefId
import org.springframework.context.support.beans
import java.util.*

val traderCliConfiguration = beans {

    bean { TraderRefId(UUID.randomUUID().toString())}

}