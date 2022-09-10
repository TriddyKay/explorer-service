package com.pokemon.explorer.domain

import java.util.*

class Team(
  val pokemonTeam: List<PokemonBasicInformation> = mutableListOf(),
  val teamId: String = UUID.randomUUID().toString()
) {

  data class Builder(
  var pokemonTeam: List<PokemonBasicInformation> = mutableListOf(),
  var teamId: String = UUID.randomUUID().toString()) {
      fun createTeam(pokemonTeam: List<PokemonBasicInformation>) = apply { this.pokemonTeam = pokemonTeam }
      fun withTeamId() = apply { this.teamId }
      fun build() = Team(pokemonTeam, teamId)
    }
}

class ExtendedInformationTeam(
  val pokemonTeam: List<PokemonExtendedInformation>,
  val teamId: String
)
