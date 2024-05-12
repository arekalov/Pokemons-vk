package com.arekalov.pokemons_vk.di

import com.arekalov.data.api.PokemonApi
import com.arekalov.pokemons_vk.MainActivity
import com.arekalov.pokemons_vk.adapters.PokemonListAdapter
import com.arekalov.pokemons_vk.adapters.PokemonLoadsAdapter
import com.arekalov.pokemons_vk.fragments.PokemonListFragment
import dagger.Component
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


@Component(modules = [NetworkModule::class, PokemonListFragmentModule::class])
interface AppComponent {
    fun inject(pokemonListFragment: PokemonListFragment)
}

@Module
object NetworkModule {
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }
}

@Module
object PokemonListFragmentModule {
    @Provides
    fun providePokemonListAdapter(): PokemonListAdapter {
        return PokemonListAdapter()
    }

    @Provides
    fun providePokemonLoadsAdapter(): PokemonLoadsAdapter {
        return PokemonLoadsAdapter()
    }
}
