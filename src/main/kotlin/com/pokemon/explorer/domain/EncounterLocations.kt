package com.pokemon.explorer.domain


data class EncounterLocations(
  val versions: List<String>,
//  val location: String,
//  val encounterDetails: EncounterDetails
) {
  companion object {
    fun from(locationsResponse: List<LocationResponse>): Any {
      val listOfVersionsFromResponse = locationsResponse.flatMap { it.collectVersionNames() }.distinct()
      val something = locationsResponse.flatMap{ it.populateLocationsByVersion(listOfVersionsFromResponse) }
//      return something
      return locationsResponse
    }
  }
}
