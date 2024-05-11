package com.arekalov.pokemons_vk.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.arekalov.pokemons_vk.MainActivity
import com.arekalov.pokemons_vk.R
import com.arekalov.pokemons_vk.databinding.FragmentPokemonListBinding
import com.arekalov.pokemons_vk.viewmodels.PokemonListViewModel
import com.arekalov.pokemons_vk.viewmodels.PokemonListViewModelFactory


class PokemonListFragment : Fragment() {
    private lateinit var binding: FragmentPokemonListBinding
//    private val productsViewModel: PokemonListViewModel by viewModels {
//        PokemonListViewModelFactory(val repo)
//    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}