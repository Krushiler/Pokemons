package com.example.pokemons.data.model

import com.google.gson.annotations.SerializedName

data class Pokemon(
    @SerializedName("base_experience") val baseExperience:Int,
    @SerializedName("height") val height:Int,
    @SerializedName("name") val name:String,
    @SerializedName("sprites") val pokemonSprites: PokemonSprites,
    @SerializedName("weight") val weight: Int
){
    public fun getInformationString():String{
        return "Base experience: $baseExperience\nHeight: $height\nWeight: $weight"
    }
}

data class PokemonSprites(
    @SerializedName("front_default") val imageSource:String
)