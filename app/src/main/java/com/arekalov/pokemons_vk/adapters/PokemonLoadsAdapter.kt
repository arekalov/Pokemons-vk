package com.arekalov.pokemons_vk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.arekalov.pokemons_vk.databinding.LoadStateBinding

class PokemonLoadsAdapter : LoadStateAdapter<PokemonLoadsAdapter.LoadStateViewHolder>() {
    override fun onBindViewHolder(
        holder: LoadStateViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoadStateViewHolder {
        val binding = LoadStateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LoadStateViewHolder(binding)
    }

    class LoadStateViewHolder(
        private val binding: LoadStateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(loadState: LoadState) {
            binding.apply {
                progressBar.isVisible = loadState is LoadState.Loading
            }
        }
    }
}

