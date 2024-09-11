package com.bakarot.weather.common.entity

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name = "location")
data class Location(
    @Id
    @Column(length = 12, nullable = false, unique = true)
    var code: String = "",

    @Column(length = 128, nullable = false)
    var cityName: String = "",

    @Column(length = 128)
    var regionName: String? = null,

    @Column(length = 64, nullable = false)
    var countryName: String = "",

    @Column(length = 2, nullable = false)
    var countryCode: String = "",

    var enabled: Boolean = false,

    var trashed: Boolean = false,
)