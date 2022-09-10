package com.pokemon.explorer.service

import com.pokemon.explorer.domain.PokemonBasicInformation
import com.pokemon.explorer.domain.Team
import org.springframework.stereotype.Component

@Component
class TeamCache {
  private val teamCache = mutableMapOf<String, Team>()

  fun fetchTeamFromCache(teamId: String): Team {
    return teamCache[teamId] ?: throw Exception("could not find team with id $teamId")
  }

  
  fun saveToCache(team: Team): Team? {
    return teamCache.put(team.teamId, team)
  }

  fun fetchAll(): MutableMap<String, Team> {
    return teamCache
  }
}
