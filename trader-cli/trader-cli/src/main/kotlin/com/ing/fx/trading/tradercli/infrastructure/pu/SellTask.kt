package com.ing.fx.trading.tradercli.infrastructure.pu

import com.hazelcast.spring.context.SpringAware
import com.ing.fx.trading.tradercli.api.model.SellCommand
import com.ing.fx.trading.tradercli.api.model.SellSucceeded
import com.ing.fx.trading.tradercli.api.service.Trader
import org.springframework.beans.factory.annotation.Autowired
import java.io.Serializable
import java.util.concurrent.Callable

@SpringAware
class SellTask(
        private val command: SellCommand
) : Callable<SellSucceeded>, Serializable {

    companion object {
        private const val serialVersionUID = -3213576343319161714L
    }

    @Autowired
    @Transient
    lateinit var trader: Trader
    override fun call(): SellSucceeded {
        return trader.sell(command)
    }


}