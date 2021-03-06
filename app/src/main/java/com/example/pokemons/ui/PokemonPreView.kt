package com.example.pokemons.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.*
import com.example.pokemons.R

class PokemonPreView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : LinearLayout(context, attrs, defStyleAttr) {

    val nameTV : TextView
    val image : ImageView
    val informationTV : TextView
    val favouriteToggle : ToggleButton

    init {
        LayoutInflater.from(context).inflate(R.layout.pokemon_item, this, true)

        nameTV = findViewById(R.id.pokemonNameTV)
        image = findViewById(R.id.pokemonImageView)
        informationTV = findViewById(R.id.pokemonInformationTV)
        favouriteToggle = findViewById(R.id.favouriteToggleButton)

    }

}