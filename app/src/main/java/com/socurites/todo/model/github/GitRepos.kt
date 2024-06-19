package com.socurites.todo.model.github

data class GitRepos(
    val id: Long,
    val name: String,
    val htmlUrl: String,
    val url: String,
    val gitUrl: String,
)