package com.socurites.todo.config

import com.socurites.todo.service.PokemonService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonModules {
    @Singleton
    @Provides
    @Named("POKEMON_API_URI")
    fun providePokemonAPI(): String = "https://pokeapi.co/api/v2/"

    @Singleton
    @Provides
    @Named("POKEMON_RETROFIT")
    fun provideRetrofit(
        @Named("POKEMON_API_URI") apiUrl: String,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun providePokemonService(
        @Named("POKEMON_RETROFIT") pokemonRetrofit: Retrofit,
    ): PokemonService = pokemonRetrofit.create(PokemonService::class.java)
}