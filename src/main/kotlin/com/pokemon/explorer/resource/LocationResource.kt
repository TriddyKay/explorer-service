package com.pokemon.explorer.resource

import com.pokemon.explorer.service.LocationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/location")
class LocationResource(
  @Autowired
  private val locationService: LocationService
) {

  @GetMapping
  @RequestMapping("/pokemon/{identifier}")
  fun getLocation(@PathVariable identifier: String): Any {
    print("triz")
    return locationService.getLocation(identifier)
  }
}
