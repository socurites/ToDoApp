package com.socurites.todo.vm.pokemon

import androidx.lifecycle.ViewModel
import com.socurites.todo.service.PokemonService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PokemonViewModel @Inject constructor(
    private val pokemonService: PokemonService,
): ViewModel() {

}