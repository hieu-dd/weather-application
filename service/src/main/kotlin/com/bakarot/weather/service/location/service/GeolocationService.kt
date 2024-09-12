package com.bakarot.weather.service.location.service

import IP2Location
import com.bakarot.weather.common.entity.Location
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.nio.file.Paths

@Service
class GeolocationService {
    companion object {
        private val LOGGER = LoggerFactory.getLogger(GeolocationService::class.java)
        private val IP2LOCATION_DB_PATH = "service/ip2locationdb/IP2LOCATION-LITE-DB3.IPV6.BIN"
        private val IP2Location = IP2Location()
    }

    init {
        val projectRootPath = System.getProperty("user.dir")
        val dbPath = Paths.get(projectRootPath, IP2LOCATION_DB_PATH).toString()
        try {
            IP2Location.open(dbPath)
        } catch (e: Exception) {
            LOGGER.error("Failed to open IP2Location database: $dbPath", e)
        }
    }

    fun getLocation(ip: String): Location {
        try {
            val result = IP2Location.ipQuery(ip)
            if (result.status != "OK") {
                LOGGER.error("Failed to get location for IP: $ip")
                throw RuntimeException("Failed to get location for IP with status: ${result.status}")
            } else {
                return Location(
                    cityName = result.city.orEmpty(),
                    regionName = result.region,
                    countryName = result.countryLong.orEmpty(),
                    countryCode = result.countryShort.orEmpty(),
                )
            }
        } catch (e: Exception) {
            LOGGER.error("Failed to get location for IP: $ip", e)
            throw RuntimeException("Failed to get location for IP: $ip")
        }

    }
}