package com.example.pokemons.data.repository

import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.retrofit.NetworkService
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class SearchRepository(private val networkService: NetworkService) {
    public fun searchPokemonByName(name: String) : Single<Pokemon> {
        return networkService.getPokemonAPI().getPokemonByName(name.lowercase())
            .subscribeOn(Schedulers.io())
    }
}