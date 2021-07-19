package com.example.pokemons.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PokemonEntity (
    @PrimaryKey val id:Int
)