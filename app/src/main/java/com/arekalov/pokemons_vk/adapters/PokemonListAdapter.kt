package com.arekalov.pokemons_vk.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.arekalov.data.Status
import com.arekalov.pokemons_vk.databinding.PokemonCardBinding

class PokemonListAdapter : PagingDataAdapter<Status, PokemonViewBinding>(PRODUCT_DIFF_CALLBACK) {
    var onClick: ((Status) -> Unit)? = null
    override fun onBindViewHolder(holder: PokemonViewBinding, position: Int) {
        val status = getItem(position)
        if (status != null) {
            holder.bind(status)
        }
        holder.itemView.setOnClickListener {
            onClick!!.invoke(getItem(position)!!)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewBinding {
        return PokemonViewBinding(
            PokemonCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    companion object {
        private val PRODUCT_DIFF_CALLBACK = object : DiffUtil.ItemCallback<Status>() {
            override fun areItemsTheSame(oldItem: Status, newItem: Status): Boolean {
                if (oldItem::class.java != newItem::class.java) return false
                if (oldItem is Status.ERROR && oldItem.error == (newItem as Status.ERROR).error) return true
                if (oldItem is Status.OK<*> && oldItem.data == (newItem as Status.OK<*>).data) return true
                return false
            }

            override fun areContentsTheSame(oldItem: Status, newItem: Status): Boolean =
                oldItem == newItem
        }
    }

}
