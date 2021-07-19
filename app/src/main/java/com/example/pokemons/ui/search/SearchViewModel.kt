package com.example.pokemons.ui.search

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.Repository
import com.example.pokemons.di.viewModelsModule
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable

class SearchViewModel(application: Application, private val repository: Repository) : AndroidViewModel(application) {

    val foundPokemon : MutableLiveData<Pokemon> = MutableLiveData()
    val isFoundPokemon : MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading : MutableLiveData<Boolean> = MutableLiveData(false)

    val searchDisposable = CompositeDisposable()

    fun searchPokemonByName(name: String){
        searchDisposable.clear()
        isLoading.postValue(true)
        val disposable = repository.searchPokemonByName(name)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val pokemon = it
                isLoading.postValue(false)
                isFoundPokemon.postValue(true)
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
                isFoundPokemon.postValue(false)
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