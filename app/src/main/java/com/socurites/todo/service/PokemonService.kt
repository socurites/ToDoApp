package com.socurites.todo.service

import com.socurites.todo.model.pokemon.Pokemon
import com.socurites.todo.model.pokemon.PokemonPageResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonService {
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
    ): PokemonPageResponse

    @GET("pokemon/{pid}/")
    suspend fun getPokemon(@Path("pid") pid: Int): Pokemon
}