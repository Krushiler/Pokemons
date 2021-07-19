package com.example.pokemons.ui.favourite

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.data.model.Pokemon

class PokemonsAdapter(private val pokemons : MutableList<Pokemon>, private val context : Context?) : RecyclerView.Adapter<PokemonsAdapter.ItemViewHolder>() {

    public interface onFavouriteClickListener{
        fun onFavouriteClick(position : Int, checked : Boolean)
    }

    var favouriteClickListener : onFavouriteClickListener? = null

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val nameTV : TextView
        val image : ImageView
        val informationTV : TextView
        val favouriteToggle : ToggleButton

        init {
            nameTV = itemView.findViewById(R.id.pokemonNameTV)
            image = itemView.findViewById(R.id.pokemonImageView)
            informationTV = itemView.findViewById(R.id.pokemonInformationTV)
            favouriteToggle = itemView.findViewById(R.id.favouriteToggleButton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_item, parent, false)

        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.nameTV.text = pokemons[position].name
        Glide.with(context!!)
            .load(pokemons[position].pokemonSprites.imageSource)
            .into(holder.image)
        holder.informationTV.text = pokemons[position].getInformationString()
        holder.favouriteToggle.isChecked = pokemons[position].isFavourite
        holder.favouriteToggle.setOnCheckedChangeListener { compoundButton, b ->
            favouriteClickListener?.onFavouriteClick(holder.adapterPosition, b)
        }
    }

    override fun getItemCount(): Int {
        return pokemons.size
    }

}