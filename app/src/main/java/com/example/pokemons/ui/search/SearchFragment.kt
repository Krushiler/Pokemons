package com.example.pokemons.ui.search

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.pokemons.R
import com.example.pokemons.ui.PokemonPreView
import org.koin.androidx.viewmodel.ext.android.viewModel

class SearchFragment : Fragment() {
    companion object{
        fun newInstance() = SearchFragment()
    }

    private val viewModel:SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.search_fragment, container, false)
    }

    lateinit var toolbar: Toolbar

    lateinit var searchET : EditText
    lateinit var resultLayout : LinearLayout
    lateinit var notFoundTV : TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        resultLayout = view.findViewById(R.id.resultLayout)
        searchET = view.findViewById(R.id.searchET)
        notFoundTV = view.findViewById(R.id.notFoundTV)

        var pokemonPreView = PokemonPreView(requireActivity().applicationContext)
        resultLayout.addView(pokemonPreView)

        searchET.addTextChangedListener {
            if (searchET.text.toString().isNotEmpty()) {
                viewModel.searchPokemonByName(searchET.text.toString())
            }
        }

        viewModel.foundPokemon.observe(viewLifecycleOwner){
            Glide.with(activity?.applicationContext)
                    .load(it.pokemonSprites.imageSource)
                    .into(pokemonPreView.image)
            Log.d("SuckCock", it.name)
                pokemonPreView.nameTV.text = it.name
        }

        viewModel.isFoundPokemon.observe(viewLifecycleOwner){
            if(!it){
                resultLayout.visibility = GONE
                notFoundTV.visibility = VISIBLE
            }else{
                resultLayout.visibility = VISIBLE
                notFoundTV.visibility = GONE
            }
        }
    }


}