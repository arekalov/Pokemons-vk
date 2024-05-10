package com.arekalov.data.api

import com.arekalov.data.model.Pokemon
import com.arekalov.data.model.Result
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


class PokemonRemoteDataSource(val pokemonApi: PokemonApi) {
    suspend fun getPokemonList(skip: Int, offset: Int) {
        pokemonApi.getPokemonList(skip, offset)
    }

    suspend fun getPokemon(id: Int) {
        pokemonApi.getPokemon(id)
    }
}

interface PokemonApi {
    @GET("pokemon")
    suspend fun getPokemonList(
        @Query("skip") skip: Int,
        @Query("offset") offset: Int
    ): Response<List<Result>>

    @GET("pokemon/{id}")
    suspend fun getPokemon(
        @Path("id") id: Int
    ): Response<Pokemon>
}