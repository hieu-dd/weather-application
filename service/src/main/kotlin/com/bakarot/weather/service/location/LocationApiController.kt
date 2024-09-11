package com.bakarot.weather.service.location

import com.bakarot.weather.common.entity.Location
import com.bakarot.weather.service.location.service.LocationService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

@RestController
@RequestMapping("/v1/locations")
class LocationApiController(
    private val locationService: LocationService
) {
    @GetMapping
    fun getLocations(): ResponseEntity<List<Location>> {
        return ResponseEntity.ok(locationService.getLocations())
    }

    @PostMapping
    fun saveLocation(@RequestBody @Valid location: Location): ResponseEntity<Location> {
        val dbLocation = locationService.saveLocation(location)
        val uri = URI.create("/v1/locations/${dbLocation.code}")
        return ResponseEntity.created(uri).body(dbLocation)
    }

    @PutMapping("/{code}")
    fun updateLocation(@PathVariable code: String, @RequestBody @Valid location: Location): ResponseEntity<Location> {
        val dbLocation = locationService.saveLocation(location)
        return ResponseEntity.ok(dbLocation)
    }

    @DeleteMapping("/{code}")
    fun deleteLocation(@PathVariable code: String): ResponseEntity<Void> {
        locationService.removeLocation(code)
        return ResponseEntity.ok().build()
    }
}