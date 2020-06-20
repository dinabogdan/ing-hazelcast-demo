package com.ing.fx.trading.tradercli.api.model

import java.io.Serializable
import java.time.LocalDateTime
import java.time.LocalTime

inline class Time(val value: LocalTime) : Serializable

inline class DateTime(val value: LocalDateTime) : Serializable
