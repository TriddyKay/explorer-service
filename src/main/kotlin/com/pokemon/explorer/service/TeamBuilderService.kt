package com.pokemon.explorer.service

import com.pokemon.explorer.domain.ExtendedInformationTeam
import com.pokemon.explorer.domain.PokemonBasicInformation
import com.pokemon.explorer.domain.Team
import org.springframework.stereotype.Component

@Component("TeamBuilderService")
class TeamBuilderService(
  val teamCache: TeamCache,
  val pokemonService: PokemonService
) {
  fun buildAndSave(team: List<PokemonBasicInformation>): Team? {
    val builtTeam = Team.Builder()
      .createTeam(team)
      .withTeamId()
      .build()

    return teamCache.saveToCache(builtTeam)
  }

  fun getTeam(teamId: String): ExtendedInformationTeam {
    val team = teamCache.fetchTeamFromCache(teamId)
    val extendedInfoTeam = team.pokemonTeam.map { pokemonService.getPokemon(it.name) }
    return ExtendedInformationTeam(extendedInfoTeam, teamId)
  }

  fun getAll(): MutableMap<String, Team> {
    return teamCache.fetchAll()
  }
}
