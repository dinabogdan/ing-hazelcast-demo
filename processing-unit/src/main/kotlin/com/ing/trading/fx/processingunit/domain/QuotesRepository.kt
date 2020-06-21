package com.ing.trading.fx.processingunit.domain

import com.ing.fx.trading.marketclient.api.model.Quote

interface QuotesRepository {

    fun findAll(): List<Quote>

    fun addAll(quotes: List<Quote>)

    fun add(quote: Quote)
}