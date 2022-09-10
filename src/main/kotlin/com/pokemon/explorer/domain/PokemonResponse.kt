package com.pokemon.explorer.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class PokemonResponse (
  val id: Number,
  val name: String,
  val abilities: List<AbilityResponse>,
  val order: Number,
  val types: List<Type>,
  val sprites: Sprites,
  val moves: List<MovesResponse>
) {
  fun getListOfTypeInformation() = types.map { it.type }
  fun getListOfAbilities(): List<Ability> = abilities.map { Ability(it.ability.name, it.ability.url, it.is_hidden) }
  fun getMovesByVersion() = VersionByGen.values().map { it.value }.map { version -> populateMovesByVersions(version) }

  private fun populateMovesByVersions(version: String): MovesByVersion {
    val moveDetailsList = mutableListOf<MoveDetails>()
    moves.forEach { it.locateVersion(version)?.getMoveDetailsForVersion(it.move.name, moveDetailsList) }

    moveDetailsList.groupByTo(mutableMapOf(), {it.moveLearnMethod}, {it}).also { moveDetailsMap ->
      return MovesByVersion(version, moveDetailsMap).sortByLevelUp()
    }
  }
}

data class Type(
  val slot: Number,
  val type: BasicInformation
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class AbilityResponse(
  val ability: BasicInformation,
  val is_hidden: Boolean
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class Sprites(
  val front_default: String,
  val front_shiny: String
)

@JsonIgnoreProperties(ignoreUnknown = true)
data class MovesResponse(
  val move: BasicInformation,
  val version_group_details: List<VersionDetailsResponse>
) {
  fun locateVersion(version: String) = version_group_details.firstOrNull { it.version_group.name == version }
}

data class VersionDetailsResponse(
  val level_learned_at: Int,
  val move_learn_method: BasicInformation,
  val version_group: BasicInformation
) {
  fun getMoveDetailsForVersion(moveName: String, moveDetailsList: MutableList<MoveDetails>) {
    moveDetailsList.add(MoveDetails(
      moveName,
      move_learn_method.convertToEnumValue(),
      level_learned_at
    ))
  }
}
