package com.bakarot.weather.service.realtimeweather.service

import com.bakarot.weather.common.entity.Location
import com.bakarot.weather.common.entity.RealtimeWeather
import com.bakarot.weather.service.realtimeweather.repository.RealtimeWeatherRepository
import org.springframework.stereotype.Service

@Service
class RealtimeWeatherService(private val realtimeWeatherRepository: RealtimeWeatherRepository) {

    fun getByLocation(location: Location): RealtimeWeather {
        val countryCode = location.countryCode
        val cityName = location.cityName
        val realtimeWeather = realtimeWeatherRepository.findByCountryCodeAndCity(countryCode, cityName)
            ?: throw RuntimeException("Realtime weather not found for location: $location")
        return realtimeWeather
    }
}