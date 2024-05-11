package com.arekalov.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.arekalov.data.Status
import com.arekalov.data.model.Pokemon
import kotlinx.coroutines.delay
import javax.inject.Inject
import kotlin.math.max

class PokemonPagingRemoteDataSource @Inject constructor(
    private val pokemonRemoteDataSource: PokemonRemoteDataSource
) : PagingSource<Int, Status>() {
    companion object {
        const val STARTING_KEY = 0
        const val PAGE_SIZE = 20
    }

    private fun ensureValidKey(key: Int) = max(STARTING_KEY, key)

    override fun getRefreshKey(state: PagingState<Int, Status>): Int? {
        val position = state.anchorPosition ?: return null
        val status = state.closestItemToPosition(position) ?: return null
        return ensureValidKey(key = (status as Pokemon).id - (state.config.pageSize / 2))
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Status> {
        val start = params.key ?: STARTING_KEY
        try {
            val loadSize = minOf(params.loadSize, PAGE_SIZE)
            val loaded = pokemonRemoteDataSource.getPokemonList(PAGE_SIZE, start)
            return LoadResult.Page(
                data = loaded,
                prevKey = when (start) {
                    STARTING_KEY -> null
                    else -> ensureValidKey(key = start - loadSize)
                },
                nextKey = start + loadSize
            )
        } catch (ex: Exception) {
            return LoadResult.Error(ex)
        }
    }
}