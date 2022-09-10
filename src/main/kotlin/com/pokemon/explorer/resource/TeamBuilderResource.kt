package com.pokemon.explorer.resource

import com.pokemon.explorer.domain.ExtendedInformationTeam
import com.pokemon.explorer.domain.PokemonBasicInformation
import com.pokemon.explorer.domain.Team
import com.pokemon.explorer.service.TeamBuilderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.io.IOException

@RestController
@RequestMapping("/team")
class TeamBuilderResource(
  @Autowired()
  private val teamBuilderService: TeamBuilderService
) {

  @PostMapping("create")
  fun createTeam(@RequestBody pokemons: List<PokemonBasicInformation>): Team? {
    return teamBuilderService.buildAndSave(pokemons)
  }

  @GetMapping("{teamId}")
  fun getTeam(@PathVariable teamId: String): ExtendedInformationTeam = teamBuilderService.getTeam(teamId)

  @GetMapping("all")
  fun getAll(): MutableMap<String, Team> = teamBuilderService.getAll()
}
