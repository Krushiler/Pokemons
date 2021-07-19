package com.example.pokemons.data.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface PokemonDao {

    @Query("SELECT * FROM pokemonentity")
    fun getAll() : Flowable<List<PokemonEntity>>

    @Insert(onConflict = REPLACE)
    fun insertAll(pokemon: PokemonEntity) : Single<Long>

    @Delete
    fun delete(pokemon: PokemonEntity) : Single<Int>

    @Query("SELECT count(*)!=0 FROM pokemonentity WHERE id = :id")
    fun containsId(id: Int): Single<Boolean>

}