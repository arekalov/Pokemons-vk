package com.arekalov.pokemons_vk.fragments

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.arekalov.data.Status
import com.arekalov.data.model.Pokemon
import com.arekalov.pokemons_vk.MainActivity
import com.arekalov.pokemons_vk.adapters.PokemonListAdapter
import com.arekalov.pokemons_vk.adapters.PokemonLoadsAdapter
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
    private lateinit var loadsAdapter: PokemonLoadsAdapter
    private var internetAvailable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonListBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val looper = Handler(Looper.getMainLooper())

        val connectivityManager =
            requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                internetAvailable = true
                Log.e("!", "!!!!!!!!!!connect")
                looper.post {
                    setUpAll()
                }
            }

            override fun onLost(network: Network) {
                super.onLost(network)
                internetAvailable = false
                Log.e("!", "!!!!!!!!!!lost")
                looper.post {
                    changeInternetErrorVisibility(true)
                }
            }

        })
        if (connectivityManager.activeNetwork == null) {
            internetAvailable = false
            changeInternetErrorVisibility(true)
        }
    }

    private fun setUpAll() {
        changeInternetErrorVisibility(false)
        changeProgressBarVisibility(true)
        pokemonListViewModel.getPokemons()
        setAdapter()
        observePokemons()
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
        loadsAdapter = PokemonLoadsAdapter()
        pokemonListAdapter = PokemonListAdapter()
        binding.rvPokemons.apply {
            adapter = pokemonListAdapter.withLoadStateFooter(loadsAdapter)
            layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        }
    }

    private fun observePokemons() {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                if (internetAvailable) {
                    pokemonListViewModel.getPokemons()?.observe(viewLifecycleOwner) {
                        pokemonListAdapter.submitData(lifecycle, it)
                    }
                    pokemonListAdapter.addLoadStateListener {
                        if (it.refresh is LoadState.NotLoading && pokemonListAdapter.itemCount > 0) {
                            changeProgressBarVisibility(false)
                        }
                    }
                }
            } catch (ex: Throwable) {
                Log.e("error", "observeProducts: ${ex.message}")
            }
        }
    }

    private fun changeProgressBarVisibility(isVisible: Boolean) {
        if (isVisible) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.INVISIBLE
    }

    private fun changeInternetErrorVisibility(isVisible: Boolean) {
        if (isVisible) {
            binding.rvPokemons.visibility = View.INVISIBLE
            binding.tcNoInternet.visibility = View.VISIBLE
            binding.ivNoInternet.visibility = View.VISIBLE
        } else {
            binding.rvPokemons.visibility = View.VISIBLE
            binding.tcNoInternet.visibility = View.INVISIBLE
            binding.ivNoInternet.visibility = View.INVISIBLE
        }
    }
}