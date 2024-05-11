package com.arekalov.data.api

import com.arekalov.data.Status

class PokemonRepository(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    private val pokemonPagingRemoteDataSource: PokemonPagingRemoteDataSource
) {
        suspend fun getPokemonList(limit: Int, offset: Int): Status {
        return pokemonRemoteDataSource.getPokemonList(limit, offset)
    }

    suspend fun getPokemon(name: String): Status {
        return pokemonRemoteDataSource.getPokemon(name)
    }
}