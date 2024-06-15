package com.socurites.todo.service.github

import com.socurites.todo.model.github.GitRepository
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {
    @GET("users/{user}/repos")
    suspend fun listRepositories(@Path("user") user: String): List<GitRepository>
}