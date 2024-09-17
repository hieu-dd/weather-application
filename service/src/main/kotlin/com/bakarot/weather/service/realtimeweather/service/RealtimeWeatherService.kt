package com.bakarot.weather.service.realtimeweather.service

import com.bakarot.weather.common.entity.Location
import com.bakarot.weather.common.entity.RealtimeWeather
import com.bakarot.weather.service.location.repository.LocationRepository
import com.bakarot.weather.service.realtimeweather.repository.RealtimeWeatherRepository
import org.springframework.stereotype.Service
import java.util.Date

@Service
class RealtimeWeatherService(
    private val realtimeWeatherRepository: RealtimeWeatherRepository,
    private val locationRepository: LocationRepository,
) {

    fun getByLocation(location: Location): RealtimeWeather {
        val countryCode = location.countryCode
        val cityName = location.cityName
        val realtimeWeather = realtimeWeatherRepository.findByCountryCodeAndCity(countryCode, cityName)
            ?: throw RuntimeException("Realtime weather not found for location: $location")
        return realtimeWeather
    }

    fun getByLocationCode(code: String): RealtimeWeather {
        val realtimeWeather = realtimeWeatherRepository.findByLocationCode(code)
            ?: throw RuntimeException("Realtime weather not found for location code: $code")
        return realtimeWeather
    }

    fun updateByLocationCode(code: String, realtimeWeather: RealtimeWeather): RealtimeWeather {
        val location = locationRepository.findById(code).orElseThrow {
            RuntimeException("Location not found for code: $code")
        }
        val updatedRealtimeWeather = realtimeWeather.copy(
            location = location,
            lastUpdated = Date().time,
        )
        if (location.realtimeWeather == null) {
            location.realtimeWeather = updatedRealtimeWeather
            return locationRepository.save(location).realtimeWeather!!
        }
        return realtimeWeatherRepository.save(updatedRealtimeWeather)
    }
}