package com.pokemon.explorer.domain

class BasicInformation(
  val name: String,
  val url: String
) {
  fun convertToEnumValue(): MoveLearnMethod {
    return when(name) {
      "tutor" -> MoveLearnMethod.TUTOR
      "machine" -> MoveLearnMethod.MACHINE
      else -> MoveLearnMethod.LEVEL_UP
    }
  }
}
