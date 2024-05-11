package com.arekalov.pokemons_vk.adapters

import androidx.recyclerview.widget.RecyclerView
import com.arekalov.data.Status
import com.arekalov.data.model.Pokemon
import com.arekalov.pokemons_vk.databinding.PokemonCardBinding
import com.bumptech.glide.Glide

class PokemonViewBinding(
    private val binding: PokemonCardBinding
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(status: Status) {
        binding.apply {
            if (status is Status.OK<*>) {
                binding.tvName.text = (status.data as Pokemon).name
                binding.tvId.text = (status.data as Pokemon).id.toString()
                Glide.with(itemView)
                    .load(((status as Status.OK<*>).data as Pokemon).sprites.front_default)
                    .into(binding.ivImage)
            } else {
                binding.tvName.text = "Loading error!"
            }
        }
    }
}