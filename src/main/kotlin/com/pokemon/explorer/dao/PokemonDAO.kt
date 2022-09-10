package com.pokemon.explorer.dao

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.pokemon.explorer.domain.AllPokemonResponse
import com.pokemon.explorer.domain.PokemonResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Component

@Component("PokemonDAO")
class PokemonDAO {
  private val client = OkHttpClient()
  private val mapper = jacksonObjectMapper()

  fun getListOfPokemon(): AllPokemonResponse {
    try {
      val request = Request.Builder().url("https://pokeapi.co/api/v2/pokemon/?&limit=1126").build()
      val response = client.newCall(request).execute()
      return mapper.readValue(response.body!!.string())
    } catch (e: Exception) {
      throw e
    }
  }

  fun getPokemon(identifier: String): PokemonResponse {
    try {
      val request: Request = Request.Builder().url("https://pokeapi.co/api/v2/pokemon/${identifier}").build()
      val response = client.newCall(request).execute()
      return mapper.readValue(response.body!!.string())
    } catch (e: Throwable) {
      throw e
    }
  }
}
