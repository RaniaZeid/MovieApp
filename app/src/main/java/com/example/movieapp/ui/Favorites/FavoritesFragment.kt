package com.example.movieapp.ui.Favorites

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.movieapp.R
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    private lateinit var favoriteAdapter: FavoritesAdapter
    private lateinit var favoritesViewModel: FavoritesViewModel
    val arguments: FavoritesFragmentArgs by navArgs()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        favoritesViewModel =
                ViewModelProvider(this).get(FavoritesViewModel::class.java)
        return inflater.inflate(R.layout.fragment_favorites, container, false)

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

setUpRecyclerView()
        setUpObservers()
    }

    private fun setUpRecyclerView() {
        favoriteAdapter=FavoritesAdapter{
            view, moviesName, i ->
        }
        favoritesRecyclerView.adapter=favoriteAdapter
    }

    private fun setUpObservers() {

        favoritesViewModel.addFavoriteMovies(arguments.MovieTitle)

        favoritesViewModel.getFavoriteMovies().observe(viewLifecycleOwner, Observer {

            favoriteAdapter.swapData(it)
        })

    }
}
