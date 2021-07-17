package com.example.pokemons.ui.search

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.SearchRepository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import org.reactivestreams.Subscriber

class SearchViewModel(application: Application, private val repository: SearchRepository) : AndroidViewModel(application) {

    val foundPokemon : MutableLiveData<Pokemon> = MutableLiveData()
    val isFoundPokemon : MutableLiveData<Boolean> = MutableLiveData(false)
    val searchDisposable = CompositeDisposable()

    public fun searchPokemonByName(name: String){
        searchDisposable.clear() 
        val disposable = repository.searchPokemonByName(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                foundPokemon.postValue(it)
                isFoundPokemon.postValue(true)
            },{
                isFoundPokemon.postValue(false)
            })
        searchDisposable.add(disposable)
    }



}