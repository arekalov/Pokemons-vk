package com.arekalov.data.api

import com.arekalov.data.Status
import javax.inject.Inject

class PokemonRepository @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource,
    val pokemonPagingRemoteDataSource: PokemonPagingRemoteDataSource
) {
    suspend fun getPokemonList(limit: Int, offset: Int): List<Status> {
        return pokemonRemoteDataSource.getPokemonList(limit, offset)
    }

    suspend fun getPokemon(name: String): Status {
        return pokemonRemoteDataSource.getPokemon(name)
    }
}