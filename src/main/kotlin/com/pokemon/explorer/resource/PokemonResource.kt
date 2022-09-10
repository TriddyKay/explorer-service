package com.pokemon.explorer.resource

import com.pokemon.explorer.domain.PokemonExtendedInformation
import com.pokemon.explorer.service.PokemonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/pokemon")
class PokemonResource(
  @Autowired()
  private val pokemonService: PokemonService
) {
  @GetMapping
  @RequestMapping("/{identifier}")
  fun getPokemon(@PathVariable identifier: String): PokemonExtendedInformation {
    return pokemonService.getPokemon(identifier)
  }
}
