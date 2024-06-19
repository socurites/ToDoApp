package com.socurites.todo.model.pokemon

data class Pokemon (
    val species: Species,
    val sprites: Sprites,
) {
    data class Species(
        val name: String,
    )
    data class Sprites(
        val frontDefault: String,
    )
}

data class PokemonPageResponse (
    val count: Int,
    val previous: String?,
    val next: String?,
    val results: List<Result>
) {
    data class Result(
        val url: String,
        val name: String,
    )
}