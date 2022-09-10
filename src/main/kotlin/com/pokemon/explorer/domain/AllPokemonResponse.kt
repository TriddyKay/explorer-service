package com.pokemon.explorer.domain

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

@JsonIgnoreProperties(ignoreUnknown = true)
data class AllPokemonResponse(
  val count: Int,
  val results: List<PokemonBasicInformation>
)
