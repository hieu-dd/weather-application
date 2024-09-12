package com.bakarot.weather.common.entity

import jakarta.persistence.*

@Entity
@Table(name = "realtime_weather")
data class RealtimeWeather(
    @Id
    @Column(name = "location_code", nullable = false)
    val locationCode: String,
    val temperature: Double,
    val humidity: Double,
    val windSpeed: Double,
    val precipitation: Double,
    val status: String,
    val lastUpdated: Long,
    @OneToOne
    @JoinColumn(name = "location_code")
    @MapsId
    val location: Location,
)