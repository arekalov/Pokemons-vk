package com.arekalov.data.api

import com.arekalov.data.model.Pokemon
import com.arekalov.data.model.Result


class PokemonRemoteDataSource(private val pokemonApi: PokemonApi) {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Result> {
        return pokemonApi.getPokemonList(limit, offset).results
    }

    suspend fun getPokemon(name: String): Pokemon {
        return pokemonApi.getPokemon(name)
    }
}

