package com.example.pokemons.ui.favourite

import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.R
import com.example.pokemons.data.model.Pokemon
import com.example.pokemons.ui.random.RandomViewModel
import com.example.pokemons.ui.search.SearchFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavouriteFragment : Fragment() {
    companion object{
        fun newInstance() = FavouriteFragment()
    }

    private val viewModel: FavouriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.favourite_fragment, container, false)
    }

    lateinit var toolbar: Toolbar

    lateinit var favouriteRecyclerView: RecyclerView
    var pokemons : MutableList<Pokemon> = mutableListOf()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }

        favouriteRecyclerView = view.findViewById(R.id.favouritePokemonsRecyclerView)
        val favouriteRVLayoutManager = LinearLayoutManager(activity?.applicationContext)
        val favouriteAdapter = PokemonsAdapter(pokemons, activity?.applicationContext)

        favouriteAdapter.favouriteClickListener = object : PokemonsAdapter.onFavouriteClickListener{
            override fun onFavouriteClick(position: Int, checked : Boolean) {
                if (checked){
                    viewModel.savePokemonToFavourite(pokemons[position])
                }else{
                    viewModel.deletePokemonFromFavourite(pokemons[position])
                }
            }

        }
        favouriteRecyclerView.layoutManager = favouriteRVLayoutManager
        favouriteRecyclerView.adapter = favouriteAdapter
        favouriteRecyclerView.addItemDecoration(PokemonsItemDecoration(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 50f, resources.displayMetrics).toInt()))

        viewModel.favouritePokemonsLiveData.observe(viewLifecycleOwner){
            pokemons.clear()
            pokemons.addAll(it)
            favouriteAdapter.notifyDataSetChanged()
        }

    }
}