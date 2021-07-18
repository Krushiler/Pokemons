package com.example.pokemons.ui.random

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.SearchRepository
import com.example.pokemons.util.MAX_ID
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class  RandomViewModel(application: Application, private val repository: SearchRepository) : AndroidViewModel(application)  {

    val foundPokemon : MutableLiveData<Pokemon> = MutableLiveData()
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)

    val searchDisposable = CompositeDisposable()

    public fun getRandomPokemon(){
        searchDisposable.clear()
        isLoading.postValue(true)
        var randomId = (1..MAX_ID).random()
        val disposable = repository.searchPokemonById(randomId)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                isLoading.postValue(false)
                foundPokemon.postValue(it)
            },{
                isLoading.postValue(false)
            })
        searchDisposable.add(disposable)
    }

}