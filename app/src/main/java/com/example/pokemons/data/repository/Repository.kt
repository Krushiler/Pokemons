package com.example.pokemons.data.repository

import android.util.Log
import com.example.pokemons.data.database.PokemonEntity
import com.example.pokemons.data.database.PokemonsDatabase
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.retrofit.NetworkService
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class Repository(private val networkService : NetworkService, private val database: PokemonsDatabase) {
    fun searchPokemonByName(name: String) : Single<Pokemon> {
        return networkService.getPokemonAPI().getPokemonByName(name.lowercase())
            .subscribeOn(Schedulers.io())
    }

    fun searchPokemonById(id : Int) : Single<Pokemon>{
        return networkService.getPokemonAPI().getPokemonById(id)
            .subscribeOn(Schedulers.io())
    }

    fun addToFavourite(id: Int){
        val pokemonEntity = PokemonEntity(id)
        database.pokemonDao.insertAll(pokemonEntity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun deleteFromFavourite(id: Int){
        val pokemonEntity = PokemonEntity(id)
        database.pokemonDao.delete(pokemonEntity).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe()
    }

    fun isPokemonFavourite(id: Int) : Single<Boolean>{
        return database.pokemonDao.containsId(id).subscribeOn(Schedulers.io())
    }

    fun getFavouritePokemonIds() : Flowable<List<PokemonEntity>>{
        return database.pokemonDao.getAll().subscribeOn(Schedulers.io())
    }

    fun getPokemonListByIds(ids : List<Int>) : List<Observable<Pokemon>> {
        val singleList : MutableList<Observable<Pokemon>> = mutableListOf()
        for (id in ids){
            singleList.add(searchPokemonById(id).toObservable())
        }
        return singleList
    }

}