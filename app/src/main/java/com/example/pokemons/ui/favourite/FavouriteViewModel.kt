package com.example.pokemons.ui.favourite

import android.app.Application
import android.os.Debug
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.pokemons.data.database.PokemonEntity
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.data.repository.Repository
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*

class FavouriteViewModel(application: Application, private val repository: Repository) : AndroidViewModel(application)  {

    val favouritePokemonsLiveData : MutableLiveData<List<Pokemon>> = MutableLiveData()

    private val favouritesDisposable = CompositeDisposable()

    init {
        getFavourites()
    }

    private fun getFavourites(){
        favouritesDisposable.clear()
        val disposable = repository.getFavouritePokemonIds()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { pokemonEnrities ->
                    val pokeList : MutableList<Pokemon> = mutableListOf()
                    val ids : MutableList<Int> = mutableListOf()
                    for (i in pokemonEnrities){
                        ids.add(i.id)
                    }
                    Observable.concat(repository.getPokemonListByIds(ids)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                        .subscribe(object : Observer<Pokemon>{
                            override fun onSubscribe(d: Disposable) {
                            }

                            override fun onNext(t: Pokemon) {
                                t.isFavourite = true
                                pokeList.add(t)
                                favouritePokemonsLiveData.postValue(pokeList)
                            }

                            override fun onError(e: Throwable) {
                            }

                            override fun onComplete() {
                            }

                        })
                },{}
            )
        favouritesDisposable.addAll(disposable)
    }

    fun savePokemonToFavourite(pokemon: Pokemon){
        repository.addToFavourite(pokemon.id)
    }

    fun deletePokemonFromFavourite(pokemon: Pokemon){
        repository.deleteFromFavourite(pokemon.id)
    }

    override fun onCleared() {
        favouritesDisposable.dispose()
        super.onCleared()
    }

}