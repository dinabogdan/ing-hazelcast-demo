package com.ing.fx.trading.tradercli.api

import com.ing.fx.trading.tradercli.application.channel.TradingChannel
import com.ing.fx.trading.tradercli.application.channel.model.TradeCommand
import com.ing.fx.trading.tradercli.application.channel.model.TraderId
import org.springframework.web.reactive.function.server.*

fun route(
        traderId: TraderId,
        tradingChannel: TradingChannel
): RouterFunction<ServerResponse> = router {
    POST("/buy") { request -> ok().body(tradingChannel.buy(request
    .bodyToMono(TradeCommand.BuyCommand::class.java))) }
}