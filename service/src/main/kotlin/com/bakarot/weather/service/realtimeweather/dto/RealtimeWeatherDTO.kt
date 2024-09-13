package com.bakarot.weather.service.realtimeweather.dto

data class RealtimeWeatherDTO(
    var temperature: Double = 0.0,
    var humidity: Double = 0.0,
    var windSpeed: Double = 0.0,
    var precipitation: Double = 0.0,
    var status: String = "",
    var lastUpdated: Long = 0L,
    open var location: String = "",
)