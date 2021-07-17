package com.example.pokemons.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.pokemons.R
import com.example.pokemons.ui.search.SearchFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment() {
    companion object{
        fun newInstance() = MenuFragment()
    }

    private val viewModel:MenuViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    lateinit var goToSearchButton : Button
    lateinit var goToRandomButton : Button
    lateinit var goToFavouriteButton : Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        goToSearchButton = view.findViewById(R.id.goToSearchButton)
        goToRandomButton = view.findViewById(R.id.goToRandomButton)
        goToFavouriteButton = view.findViewById(R.id.goToFavouriteButton)

        goToFavouriteButton.setOnClickListener {
            viewModel.goToFavourite()
        }

        goToRandomButton.setOnClickListener {
            viewModel.goToRandom()
        }

        goToSearchButton.setOnClickListener {
            viewModel.goToSearch()
        }

    }
}