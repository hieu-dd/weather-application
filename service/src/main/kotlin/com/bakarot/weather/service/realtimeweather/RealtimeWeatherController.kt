package com.bakarot.weather.service.realtimeweather

import com.bakarot.weather.service.location.service.GeolocationService
import com.bakarot.weather.service.realtimeweather.service.RealtimeWeatherService
import com.bakarot.weather.service.util.CommonUtility
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import kotlin.reflect.jvm.internal.impl.descriptors.Visibilities.Private

@RestController
@RequestMapping("/v1/realtime")
class RealtimeWeatherController(
    private val realtimeWeatherService: RealtimeWeatherService,
    private val geolocationService: GeolocationService
) {
    @GetMapping
    public fun getRealtimeWeatherByIpAddress(request: HttpServletRequest): ResponseEntity<Any> {
       val ipAddress = CommonUtility.getIPAddress(request)
        try {
            val locationFromIp  = geolocationService.getLocation(ipAddress)
            val realtimeWeather = realtimeWeatherService.getByLocation(locationFromIp)
            return ResponseEntity.ok(realtimeWeather)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}