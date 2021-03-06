package com.ing.fx.trading.tradercli.infrastructure.pu

import com.hazelcast.spring.context.SpringAware
import com.ing.fx.trading.tradercli.api.model.BuyCommand
import com.ing.fx.trading.tradercli.api.model.BuySucceeded
import com.ing.fx.trading.tradercli.api.service.Trader
import org.springframework.beans.factory.annotation.Autowired
import java.io.Serializable
import java.util.concurrent.Callable

@SpringAware
class BuyTask(
        private val command: BuyCommand
) : Callable<BuySucceeded>, Serializable {

    companion object {
        private const val serialVersionUID = -3213576961319161714L
    }

    @Autowired
    @Transient
    lateinit var trader: Trader

    override fun call(): BuySucceeded {
        return trader.buy(command)
    }
}