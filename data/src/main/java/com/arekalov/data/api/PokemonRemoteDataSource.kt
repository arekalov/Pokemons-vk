package com.arekalov.data.api

import com.arekalov.data.Status


class PokemonRemoteDataSource(
    private val pokemonApi: PokemonApi
) {
    suspend fun getPokemon(name: String): Status {
        try {
            val pokemon = pokemonApi.getPokemon(name)
            return Status.OK(pokemon)
        } catch (ex: Exception) {
            return Status.ERROR(ex.message.toString())
        }
    }

    suspend fun getPokemonList(limit: Int = 20, offset: Int = 0): List<Status> {
        try {
            val idList = pokemonApi.getPokemonList(limit, offset).results
            val ans = mutableListOf<Status>()
            if (idList.isEmpty()) return listOf<Status>()
            for (pokemon in idList) {
                val name = pokemon.name
                ans.add(getPokemon(name))

            }
            return ans
        } catch (ex: Exception) {
            return listOf<Status>()
        }
    }

}

