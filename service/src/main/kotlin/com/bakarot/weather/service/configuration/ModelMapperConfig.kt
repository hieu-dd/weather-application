package com.bakarot.weather.service.configuration

import com.bakarot.weather.common.entity.RealtimeWeather
import com.bakarot.weather.service.realtimeweather.dto.RealtimeWeatherDTO
import org.modelmapper.AbstractConverter
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ModelMapperConfig {
    @Bean
    fun modelMapper(): ModelMapper {
        val mapper = ModelMapper()
        mapper.addConverter(object : AbstractConverter<RealtimeWeather, RealtimeWeatherDTO>() {
            override fun convert(source: RealtimeWeather): RealtimeWeatherDTO {
                return RealtimeWeatherDTO(
                    temperature = source.temperature,
                    humidity = source.humidity,
                    windSpeed = source.windSpeed,
                    precipitation = source.precipitation,
                    status = source.status,
                    lastUpdated = source.lastUpdated,
                    location = listOfNotNull(
                        source.location.countryName,
                        source.location.regionName,
                        source.location.cityName
                    ).joinToString(", ")
                )
            }
        })
        return mapper
    }
}