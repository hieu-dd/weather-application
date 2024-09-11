package com.bakarot.weather.service.location.repository

import com.bakarot.weather.common.entity.Location
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface LocationRepository : CrudRepository<Location, String> {
}