package com.example.pokemons

import com.example.pokemons.ui.favourite.FavouriteFragment
import com.example.pokemons.ui.menu.MenuFragment
import com.example.pokemons.ui.random.RandomFragment
import com.example.pokemons.ui.search.SearchFragment
import com.github.terrakok.cicerone.androidx.FragmentScreen

@Suppress("FunctionName")
object Screens {
    fun SearchScreen() = FragmentScreen { SearchFragment() }
    fun RandomScreen() = FragmentScreen { RandomFragment() }
    fun FavouriteScreen() = FragmentScreen { FavouriteFragment() }
    fun MenuScreen() = FragmentScreen { MenuFragment() }
}