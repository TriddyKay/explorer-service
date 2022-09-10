package com.pokemon.explorer.domain

data class PokemonExtendedInformation(
  val id: Number,
  val name: String,
  val types: List<BasicInformation>,
  val abilities: List<Ability>,
  val spritesResponse: Sprites,
  val movesByVersion: List<MovesByVersion>
) {
  companion object {
    fun from(pokemonResponse: PokemonResponse): PokemonExtendedInformation {
      return PokemonExtendedInformation(
        pokemonResponse.id,
        pokemonResponse.name,
        pokemonResponse.getListOfTypeInformation(),
        pokemonResponse.getListOfAbilities(),
        pokemonResponse.sprites,
        pokemonResponse.getMovesByVersion()
      )
    }
  }
}

data class MovesByVersion(
  val version: String,
  val movesByLearnMethod: MutableMap<MoveLearnMethod, MutableList<MoveDetails>>,
) {
  fun sortByLevelUp(): MovesByVersion {
    movesByLearnMethod[MoveLearnMethod.LEVEL_UP]?.sortedWith(Comparator{ o1, o2 ->
      val levelUpOrder = o1.levelLearnedAt.compareTo(o2.levelLearnedAt)

      return@Comparator if(levelUpOrder == 0) {
        o1.levelLearnedAt.compareTo(o2.levelLearnedAt)
      } else
        levelUpOrder
    }).also {
      if(it != null) movesByLearnMethod[MoveLearnMethod.LEVEL_UP] = it.toMutableList()
    }

    return MovesByVersion(version, movesByLearnMethod)
  }
}

data class MoveDetails(
  val moveName: String,
  val moveLearnMethod: MoveLearnMethod,
  val levelLearnedAt: Int
)

data class Ability(
  val name: String,
  val url: String,
  val isHidden: Boolean
)
