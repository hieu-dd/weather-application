package com.bakarot.weather.common.entity

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import org.hibernate.validator.constraints.Length

@Entity
@Table(name = "location")
data class Location(
    @Id
    @Column(length = 12, nullable = false, unique = true)
    @field:NotBlank(message = "Code is required")
    @field:Length(min = 3, max = 12, message = "Code must be between 3 and 12 characters")
    var code: String = "",

    @Column(length = 128, nullable = false)
    var cityName: String,

    @Column(length = 128)
    var regionName: String?,

    @Column(length = 64, nullable = false)
    var countryName: String,

    @Column(length = 2, nullable = false)
    var countryCode: String,

    var enabled: Boolean = false,

    var trashed: Boolean = false,

    @OneToOne(mappedBy = "location", cascade = [CascadeType.ALL])
    @PrimaryKeyJoinColumn
    var realtimeWeather: RealtimeWeather? = null
)