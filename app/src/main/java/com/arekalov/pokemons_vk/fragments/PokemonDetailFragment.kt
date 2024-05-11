package com.arekalov.pokemons_vk.fragments

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import com.arekalov.data.model.Pokemon
import com.arekalov.pokemons_vk.R
import com.arekalov.pokemons_vk.databinding.FragmentPokemonDetailBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch


class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpPokemon()
    }

    @RequiresApi(Build.VERSION_CODES.TIRAMISU)
    private fun setUpPokemon() {
        val pokemon = requireArguments().get("pokemon") as Pokemon
        binding.tvName.text = pokemon.name
        binding.tvId.text = resources.getString(R.string.id, pokemon.id.toString())
        binding.tvHeight.text = resources.getString(R.string.height, pokemon.height.toString())
        binding.tvWeight.text = resources.getString(R.string.weight, pokemon.weight.toString())
        binding.tvOrder.text = resources.getString(R.string.order, pokemon.order.toString())
        binding.tvIsDefault.text = resources.getString(R.string.is_default, pokemon.is_default.toString())
        binding.tvBaseExperience.text =
            resources.getString(R.string.base_experience, pokemon.base_experience.toString())
        lifecycleScope.launch {
            Glide.with(requireActivity().baseContext)
                .load(pokemon?.sprites?.front_default)
                .into(binding.ivFront)
            Glide.with(requireActivity().baseContext)
                .load(pokemon?.sprites?.back_default)
                .into(binding.ivBack)
        }
    }
}