package com.arekalov.data.api

class PokemonRepository(private val pokemonRemoteDataSource: PokemonRemoteDataSource) {
    suspend fun getPokemon(name: String): Status {
        try {
            val pokemon = pokemonRemoteDataSource.getPokemon(name)
            return Status.OK(pokemon)
        } catch (ex: Exception) {
            return Status.ERROR(ex.message.toString())
        }
    }

    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): Status {
        try {
            val idList = pokemonRemoteDataSource.getPokemonList(limit, offset)
            val ans = mutableListOf<Status>()
            if (idList.isEmpty()) return Status.ERROR("empty result")
            for (pokemon in idList) {
                val name = pokemon.name
                ans.add(getPokemon(name))
            }
            return Status.OK(ans)
        } catch (ex: Exception) {
            return Status.ERROR(ex.message.toString())
        }
    }
}