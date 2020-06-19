package com.ing.market.client.api.service

import com.ing.market.client.api.model.Quote

interface MarketClientService {

    fun publish(quotes: List<Quote>)
}