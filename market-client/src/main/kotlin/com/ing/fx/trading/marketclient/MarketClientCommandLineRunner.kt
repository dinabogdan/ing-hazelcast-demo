package com.ing.fx.trading.marketclient

import com.ing.fx.trading.marketclient.api.model.*
import com.ing.fx.trading.marketclient.application.MarketChannelPublisher
import com.ing.fx.trading.marketclient.lang.random
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import reactor.core.publisher.Flux
import java.math.BigDecimal
import java.math.MathContext
import java.time.Duration
import java.time.LocalTime
import java.time.temporal.ChronoUnit

class MarketClientCommandLineRunner(
        private val marketChannelPublisher: MarketChannelPublisher
) : CommandLineRunner {

    private val logger: Logger = LoggerFactory.getLogger(MarketClientCommandLineRunner::class.java)

    override fun run(vararg args: String?) {
        logger.info("Starting to publish market changes...")
        Flux.interval(Duration.of(1500, ChronoUnit.MILLIS))
                .doOnNext {
                    logger.info("Tick $it ... ")
                    Flux.fromArray(QuotePair.values())
                            .delayElements(Duration.of(250, ChronoUnit.MILLIS))
                            .doOnNext { quotePair ->
                                marketChannelPublisher.publish(quotePair.newQuote()).subscribe()
                            }.subscribe()
                }.subscribe()

    }

    private fun QuotePair.newQuote(): Quote {
        val pair = this
        val time = QuoteTime(LocalTime.now())
        val bid = randomBid(pair)
        val ask = randomAsk(pair, bid)
        val (low, high) = margins(pair)

        return Quote(
                pair = pair,
                time = time,
                bid = bid,
                ask = ask,
                low = low,
                high = high
        )
    }

    private fun margins(quotePair: QuotePair): Pair<QuoteLow, QuoteHigh> {
        return when (quotePair) {
            QuotePair.AUD_USD -> QuoteLow(BigDecimal.valueOf(0.6830)) to QuoteHigh(BigDecimal.valueOf(0.6950))
            QuotePair.EUR_USD -> QuoteLow(BigDecimal.valueOf(1.1168)) to QuoteHigh(BigDecimal.valueOf(1.1260))
            QuotePair.GBP_USD -> QuoteLow(BigDecimal.valueOf(1.2344)) to QuoteHigh(BigDecimal.valueOf(1.2460))
            QuotePair.USD_CAD -> QuoteLow(BigDecimal.valueOf(1.3545)) to QuoteHigh(BigDecimal.valueOf(1.3600))
            QuotePair.USD_CHF -> QuoteLow(BigDecimal.valueOf(0.9488)) to QuoteHigh(BigDecimal.valueOf(0.9560))
            QuotePair.USD_JPY -> QuoteLow(BigDecimal.valueOf(106.77)) to QuoteHigh(BigDecimal.valueOf(107.10))
        }
    }

    private fun randomAsk(quotePair: QuotePair, quoteBid: QuoteBid): QuoteAsk {
        return when (quotePair) {
            QuotePair.USD_JPY -> QuoteAsk(quoteBid.value.plus(random("0.3")))
            QuotePair.USD_CHF -> QuoteAsk(quoteBid.value.plus(random("0.3")))
            QuotePair.USD_CAD -> QuoteAsk(quoteBid.value.plus(random("0.001")))
            QuotePair.GBP_USD -> QuoteAsk(quoteBid.value.plus(random("0.01")))
            QuotePair.EUR_USD -> QuoteAsk(quoteBid.value.plus(random("0.0003")))
            QuotePair.AUD_USD -> QuoteAsk(quoteBid.value.plus(random("0.0005")))
        }
    }

    private fun randomBid(quotePair: QuotePair): QuoteBid {
        return when (quotePair) {
            QuotePair.AUD_USD -> QuoteBid(random("0.7"))
            QuotePair.EUR_USD -> QuoteBid(random("1.1200"))
            QuotePair.GBP_USD -> QuoteBid(random("1.2500"))
            QuotePair.USD_CAD -> QuoteBid(random("1.4"))
            QuotePair.USD_CHF -> QuoteBid(random("1.0"))
            QuotePair.USD_JPY -> QuoteBid(random("110.0"))
        }
    }
}




