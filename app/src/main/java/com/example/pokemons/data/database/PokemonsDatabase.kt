package com.example.pokemons.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.pokemons.data.model.Pokemon

@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonsDatabase : RoomDatabase() {
    abstract val pokemonDao : PokemonDao
}