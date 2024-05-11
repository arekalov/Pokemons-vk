package com.arekalov.pokemons_vk.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.arekalov.data.Status
import com.arekalov.data.model.Pokemon
import com.arekalov.pokemons_vk.MainActivity
import com.arekalov.pokemons_vk.R
import com.arekalov.pokemons_vk.adapters.PokemonListAdapter
import com.arekalov.pokemons_vk.databinding.FragmentPokemonListBinding
import com.arekalov.pokemons_vk.viewmodels.PokemonListViewModel
import com.arekalov.pokemons_vk.viewmodels.PokemonListViewModelFactory
import kotlinx.coroutines.launch


class PokemonListFragment : Fragment() {
    private lateinit var binding: FragmentPokemonListBinding
    private val pokemonListViewModel: PokemonListViewModel by viewModels {
        PokemonListViewModelFactory((activity as MainActivity).repository)
    }
    private lateinit var pokemonListAdapter: PokemonListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pokemonListViewModel.getPokemons()
        setAdapter()
        observeProducts()
        setOnClick()
    }

    private fun setOnClick() {
        pokemonListAdapter.onClick = {
            if (it is Status.OK<*>) {
                val action =
                    PokemonListFragmentDirections.actionPokemonListFragmentToPokemonDetatilFragment(
                        it.data as Pokemon
                    )
                findNavController().navigate(action)
            } else Toast.makeText(activity, "Invalid pokemon!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setAdapter() {
        pokemonListAdapter = PokemonListAdapter()
        binding.rvPokemons.apply {
            adapter = pokemonListAdapter
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observeProducts() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                pokemonListViewModel.getPokemons()?.observe(viewLifecycleOwner) {
                    pokemonListAdapter.submitData(lifecycle, it)
                }
            } catch (ex: Throwable) {
                Log.e("error", "observeProducts: ${ex.message}")
            }
        }
    }

}