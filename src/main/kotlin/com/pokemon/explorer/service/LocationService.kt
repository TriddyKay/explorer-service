package com.pokemon.explorer.service

import com.pokemon.explorer.domain.EncounterLocations
import com.pokemon.explorer.dao.LocationDAO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("LocationService")
class LocationService(
  @Autowired
  private val locationDAO: LocationDAO
) {

  fun getLocation(identifier: String): Any {
    locationDAO.getLocation(identifier).also { return EncounterLocations.from(it) }
  }
}
