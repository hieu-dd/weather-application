package com.bakarot.weather.service.realtimeweather

import com.bakarot.weather.common.entity.RealtimeWeather
import com.bakarot.weather.service.location.service.GeolocationService
import com.bakarot.weather.service.realtimeweather.dto.RealtimeWeatherDTO
import com.bakarot.weather.service.realtimeweather.service.RealtimeWeatherService
import com.bakarot.weather.service.util.CommonUtility
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.Valid
import org.modelmapper.ModelMapper
import org.slf4j.Logger
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/realtime")
class RealtimeWeatherController(
    private val realtimeWeatherService: RealtimeWeatherService,
    private val geolocationService: GeolocationService,
    private val modalMapper: ModelMapper,
) {
    companion object {
        val LOGGER: Logger = org.slf4j.LoggerFactory.getLogger(RealtimeWeatherController::class.java)
    }

    @GetMapping
    fun getRealtimeWeatherByIpAddress(request: HttpServletRequest): ResponseEntity<Any> {
        val ipAddress = CommonUtility.getIPAddress(request)
        try {
            val locationFromIp = geolocationService.getLocation(ipAddress)
            LOGGER.info("Location from IP: $locationFromIp.c")
            val realtimeWeather = realtimeWeatherService.getByLocation(locationFromIp)
            val dto = modalMapper.map(realtimeWeather, RealtimeWeatherDTO::class.java)
            return ResponseEntity.ok(dto)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @GetMapping("/{code}")
    fun getRealtimeWeatherByLocationCode(@PathVariable code: String): ResponseEntity<Any> {
        try {
            val realtimeWeather = realtimeWeatherService.getByLocationCode(code)
            val dto = modalMapper.map(realtimeWeather, RealtimeWeatherDTO::class.java)
            return ResponseEntity.ok(dto)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }

    @PutMapping("/{code}")
    fun updateRealtimeWeatherByLocationCode(
        @PathVariable code: String,
        @Valid @RequestBody realtimeWeather: RealtimeWeather,
    ): ResponseEntity<Any> {
        try {
            val dbRealtimeWeather = realtimeWeatherService.updateByLocationCode(code, realtimeWeather)
            val dto = modalMapper.map(dbRealtimeWeather, RealtimeWeatherDTO::class.java)
            return ResponseEntity.ok(dto)
        } catch (e: Exception) {
            return ResponseEntity.badRequest().body(e.message)
        }
    }
}