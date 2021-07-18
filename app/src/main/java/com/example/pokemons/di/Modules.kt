package com.example.pokemons.di

import com.example.pokemons.data.repository.SearchRepository
import com.example.pokemons.data.retrofit.NetworkService
import com.example.pokemons.ui.favourite.FavouriteViewModel
import com.example.pokemons.ui.menu.MenuViewModel
import com.example.pokemons.ui.random.RandomViewModel
import com.example.pokemons.ui.search.SearchViewModel
import com.github.terrakok.cicerone.Cicerone
import com.github.terrakok.cicerone.Router
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val navigationModule = module {
    single { Cicerone.create(Router()) }
    single { get<Cicerone<Router>>().router }
    single { get<Cicerone<Router>>().getNavigatorHolder() }
}

val viewModelsModule = module {
    viewModel { SearchViewModel(get(), get()) }
    viewModel { RandomViewModel(get(), get()) }
    viewModel { MenuViewModel(get(), get()) }
    viewModel { FavouriteViewModel(get()) }
}

val dataModule = module {
    single { NetworkService() }
    single { SearchRepository(get()) }
}