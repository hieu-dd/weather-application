package com.bakarot.weather.service.location.service

import com.bakarot.weather.common.entity.Location
import com.bakarot.weather.service.location.repository.LocationRepository
import org.springframework.stereotype.Service


@Service
class LocationService(private val locationRepository: LocationRepository) {
    fun saveLocation(location: Location): Location {
        val dbLocation = locationRepository.save(location)
        return dbLocation
    }

    fun getLocations(): List<Location> {
        return locationRepository.findAll().toList()
    }

    fun removeLocation(code: String) {
        locationRepository.deleteById(code)
    }

    fun getLocation(code: String): Location {
        return locationRepository.findById(code).orElseThrow {
            RuntimeException("Location not found for code: $code")
        }
    }
}