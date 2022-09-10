package com.pokemon.explorer.service

import com.pokemon.explorer.dao.PokemonDAO
import com.pokemon.explorer.domain.PokemonExtendedInformation
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component("PokemonService")
class PokemonService(
  @Autowired()
  val pokemonDAO: PokemonDAO,
) {
  fun getPokemon(identifier: String): PokemonExtendedInformation {
    pokemonDAO.getPokemon(identifier).also { pokemon ->
      return PokemonExtendedInformation.from(pokemon)
    }
  }
}
