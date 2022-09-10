package com.pokemon.explorer.service

import com.pokemon.explorer.dao.PokemonDAO
import com.pokemon.explorer.domain.PokemonBasicInformation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class PokemonCache(
  @Autowired
  val pokemonDAO: PokemonDAO
) {
  private val pokemonCacheResponse = mutableListOf<PokemonBasicInformation>()

  @PostConstruct
  fun populateCache() {
    pokemonDAO.getListOfPokemon().also {
      pokemonCacheResponse.addAll(it.results)
    }
  }

  fun getPokemonFromCache(identifier: String): PokemonBasicInformation = pokemonCacheResponse.firstOrNull { it.name == identifier } ?: throw Exception("could not find pokemon")
}
