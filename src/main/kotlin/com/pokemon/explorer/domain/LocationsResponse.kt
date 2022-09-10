package com.pokemon.explorer.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class LocationResponse(
  val location_area: BasicInformation,
  val version_details: List<VersionLocationDetails>,
) {
  fun collectVersionNames(): List<String> = version_details.map { it.version.name }
  fun populateLocationsByVersion(versionList: List<String>): List<Any> {
//    val newMap = versionList.map {it to listOf()}.toMap()
    return listOf()
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class VersionLocationDetails(
  val encounter_details: List<EncounterDetails>,
  val version: BasicInformation
)

data class EncounterDetails(
  val chance: Int,
  val condition_values: List<BasicInformation>,
  val max_level: Int,
  val min_level: Int,
  val method: BasicInformation
)

