package com.socurites.todo.config

import com.socurites.todo.service.github.GithubService
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
class GithubModules {
    @Singleton
    @Provides
    @Named("GITHUB_API_URI")
    fun provideWebAPI(): String = "https://api.github.com/"

    @Singleton
    @Provides
    @Named("GITHUB_RETROFIT")
    fun provideGithubRetrofit(
        @Named("GITHUB_API_URI") apiUrl: String,
        converterFactory: GsonConverterFactory
    ): Retrofit = Retrofit.Builder()
        .baseUrl(apiUrl)
        .addConverterFactory(converterFactory)
        .build()

    @Singleton
    @Provides
    fun provideGithubService(
        @Named("GITHUB_RETROFIT") githubRetrofit: Retrofit
    ): GithubService = githubRetrofit.create(GithubService::class.java)
}