package com.example.pokemons.ui.random

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.pokemons.R
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.ui.GlideApp
import com.example.pokemons.ui.PokemonPreView
import com.example.pokemons.ui.search.SearchFragment
import com.example.pokemons.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RandomFragment : Fragment() {
    companion object{
        fun newInstance() = RandomFragment()
    }

    private val viewModel: RandomViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.random_fragment, container, false)
    }

    lateinit var toolbar: Toolbar

    lateinit var resultLayout : LinearLayout
    lateinit var loadingPB : ProgressBar
    lateinit var getPokemonBTN : Button

    var currentPokemon: Pokemon? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        resultLayout = view.findViewById(R.id.resultLayout)
        loadingPB = view.findViewById(R.id.loadingPB)
        getPokemonBTN = view.findViewById(R.id.getRandomPokemonBTN)

        var pokemonPreView = PokemonPreView(requireActivity().applicationContext)
        resultLayout.addView(pokemonPreView)

        getPokemonBTN.setOnClickListener { viewModel.getRandomPokemon() }

        viewModel.foundPokemon.observe(viewLifecycleOwner){
            currentPokemon = it

            pokemonPreView.nameTV.text = it.name

            GlideApp.with(activity?.applicationContext!!)
                .load(it.pokemonSprites.imageSource)
                .into(pokemonPreView.image)

            pokemonPreView.informationTV.text = it.getInformationString()
            pokemonPreView.favouriteToggle.isChecked = it.isFavourite
        }

        viewModel.isLoading.observe(viewLifecycleOwner){
            if (it){
                resultLayout.visibility = View.GONE
                loadingPB.visibility = View.VISIBLE
            }else{
                resultLayout.visibility = View.VISIBLE
                loadingPB.visibility = View.GONE
            }
        }

        pokemonPreView.favouriteToggle.setOnCheckedChangeListener { compoundButton, b ->
            if (b){
                viewModel.savePokemonToFavourite(currentPokemon!!)
            }else{
                viewModel.deletePokemonFromFavourite(currentPokemon!!)
            }
        }



    }
}