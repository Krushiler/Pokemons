package com.example.pokemons.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("base_experience") val baseExperience:Int,
    @SerializedName("height") val height:Int,
    @SerializedName("name") val name:String,
    @SerializedName("sprites") val pokemonSprites: PokemonSprites,
    @SerializedName("weight") val weight: Int
)

data class PokemonSprites(
    @SerializedName("front_default") val imageSource:String
)