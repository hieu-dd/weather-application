package com.bakarot.weather.service.realtimeweather.repository


import com.bakarot.weather.common.entity.RealtimeWeather
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface RealtimeWeatherRepository : CrudRepository<RealtimeWeather, String> {
    @Query("SELECT r FROM RealtimeWeather r WHERE r.location.countryCode = ?1 AND r.location.cityName = ?2")
    fun findByCountryCodeAndCity(countryCode: String, city: String): RealtimeWeather?

    @Query("SELECT r FROM RealtimeWeather r WHERE r.locationCode = ?1 AND r.location.trashed = false")
    fun findByLocationCode(locationCode: String): RealtimeWeather?
}