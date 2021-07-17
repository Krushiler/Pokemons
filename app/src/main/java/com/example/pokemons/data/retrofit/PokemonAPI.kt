package com.example.pokemons.data.retrofit

import com.example.pokemons.data.model.Pokemon
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonAPI {
    @GET("pokemon/{name}")
    fun getPokemonByName(@Path(value = "name", encoded = true) name: String) : Single<Pokemon>

    @GET("pokemon/{id}")
    fun getPokemonById(@Path(value = "id", encoded = true) id : Int) : Single<Pokemon>
}