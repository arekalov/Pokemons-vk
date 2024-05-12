package com.arekalov.pokemons_vk

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.arekalov.data.api.PokemonRepository
import com.arekalov.pokemons_vk.di.AppComponent
import com.arekalov.pokemons_vk.di.DaggerAppComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    internal lateinit var appComponent : AppComponent
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent = DaggerAppComponent.create()
    }
}
