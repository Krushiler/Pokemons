package com.example.pokemons.ui.random

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.pokemons.R
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        toolbar = view.findViewById(R.id.toolbar)
        toolbar.setNavigationOnClickListener { activity?.onBackPressed() }
    }
}