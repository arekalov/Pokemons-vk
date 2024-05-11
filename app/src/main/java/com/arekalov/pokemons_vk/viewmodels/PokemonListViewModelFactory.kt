package com.arekalov.pokemons_vk.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.arekalov.data.api.PokemonRepository
import javax.inject.Inject

class PokemonListViewModelFactory @Inject constructor(
    private val repository: PokemonRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return PokemonListViewModel(repository) as T
    }
}