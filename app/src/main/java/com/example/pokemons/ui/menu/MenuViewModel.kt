package com.example.pokemons.ui.menu

import android.app.Application
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.AndroidViewModel
import com.example.pokemons.R
import com.example.pokemons.Screens
import com.github.terrakok.cicerone.Router
import com.github.terrakok.cicerone.Screen
import com.github.terrakok.cicerone.androidx.AppNavigator
import com.github.terrakok.cicerone.androidx.FragmentScreen

class MenuViewModel(application: Application, private val router: Router) : AndroidViewModel(application)  {

    private fun navigateToScreen(screenToNavigate: Screen){
        router.navigateTo(screenToNavigate)
    }

    public fun goToSearch(){
        navigateToScreen(Screens.SearchScreen())
    }

    public fun goToRandom(){
        navigateToScreen(Screens.RandomScreen())
    }

    public fun goToFavourite(){
        navigateToScreen(Screens.FavouriteScreen())
    }

}