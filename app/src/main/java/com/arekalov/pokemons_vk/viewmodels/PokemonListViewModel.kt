package com.arekalov.pokemons_vk.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.liveData
import com.arekalov.data.Status
import com.arekalov.data.api.PokemonRepository

class PokemonListViewModel(private val repository: PokemonRepository) : ViewModel() {
    companion object {
        private const val ITEMS_PER_PAGE = 20
    }
    private var pokemonsLiveData = MutableLiveData<PagingData<Status>>()
    private val response = Pager(
        config = PagingConfig(pageSize = ITEMS_PER_PAGE, enablePlaceholders = false),
        pagingSourceFactory = { repository.pokemonPagingRemoteDataSource }
    ).liveData
        .cachedIn(viewModelScope)

    fun getPokemons(): LiveData<PagingData<Status>>? {
        try {
            pokemonsLiveData.value = response.value
            return response
        } catch (ex: Exception) {
            Log.e("error", "getProducts: response is null")
            return null
        }
    }

}