package com.example.pokemons.ui.random

import android.app.Application
import android.os.Debug
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.Repository
import com.example.pokemons.util.MAX_ID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class  RandomViewModel(application: Application, private val repository: Repository) : AndroidViewModel(application)  {

    val foundPokemon : MutableLiveData<Pokemon> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)

    val searchDisposable = CompositeDisposable()

    init {
        getRandomPokemon()
    }

    fun getRandomPokemon(){
        searchDisposable.clear()
        isLoading.postValue(true)
        var randomId = (1..MAX_ID).random()
        val disposable = repository.searchPokemonById(randomId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val pokemon = it
                isLoading.postValue(false)
                repository.isPokemonFavourite(it.id).observeOn(AndroidSchedulers.mainThread())
                    .subscribe({ isFound ->
                        pokemon.isFavourite = isFound
                        foundPokemon.postValue(pokemon)
                    },{
                        pokemon.isFavourite = false
                        foundPokemon.postValue(pokemon)
                    })
            },{
                isLoading.postValue(false)
            })
        searchDisposable.add(disposable)
    }


    fun savePokemonToFavourite(pokemon: Pokemon){
        repository.addToFavourite(pokemon.id)
    }

    fun deletePokemonFromFavourite(pokemon: Pokemon){
        repository.deleteFromFavourite(pokemon.id)
    }

    override fun onCleared() {
        searchDisposable.dispose()
        super.onCleared()
    }

}