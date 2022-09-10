package com.pokemon.explorer.dao

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.pokemon.explorer.domain.LocationResponse
import okhttp3.OkHttpClient
import okhttp3.Request
import org.springframework.stereotype.Component

@Component("LocationDAO")
class LocationDAO {
  private val client = OkHttpClient()
  private val mapper = jacksonObjectMapper()

  fun getLocation(identifier: String): List<LocationResponse> {
    val request: Request = Request.Builder().url("https://pokeapi.co/api/v2/pokemon/${identifier}/encounters").build()
    val response = client.newCall(request).execute()
    return mapper.readValue(response.body!!.string())
  }
}
