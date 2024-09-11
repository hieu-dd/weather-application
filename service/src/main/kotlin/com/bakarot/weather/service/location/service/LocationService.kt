package com.bakarot.weather.service.location.service

import com.bakarot.weather.common.entity.Location
import com.bakarot.weather.service.location.repository.LocationRepository
import org.springframework.stereotype.Service


interface LocationService {
    fun getLocations(): List<Location>
    fun saveLocation(location: Location): Location
    fun removeLocation(code: String)
}

@Service
class LocationServiceImpl(private val locationRepository: LocationRepository) : LocationService {
    override fun saveLocation(location: Location): Location {
        val dbLocation = locationRepository.save(location)
        return dbLocation
    }

    override fun getLocations(): List<Location> {
        return locationRepository.findAll().toList()
    }

    override fun removeLocation(code: String) {
        locationRepository.deleteById(code)
    }
}