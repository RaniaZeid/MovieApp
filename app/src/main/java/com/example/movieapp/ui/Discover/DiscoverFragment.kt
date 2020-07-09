package com.example.movieapp.ui.Discover

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movieapp.R
import kotlinx.android.synthetic.main.fragment_discover.*

class DiscoverFragment : Fragment() {

    private lateinit var popularMoviesAdapter: PopularMoviesAdapter
    private lateinit var discoverViewModel: DiscoverViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        discoverViewModel =
            ViewModelProvider(this).get(DiscoverViewModel::class.java)
        return inflater.inflate(R.layout.fragment_discover, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObserver()
    }

    private fun setUpRecyclerView() {
        popularMoviesAdapter=PopularMoviesAdapter{
            view, resultsItem, i ->
            val Action=DiscoverFragmentDirections.actionNavigationDiscoverToNavigationDetails(
                resultsItem.id
            )
            findNavController().navigate(Action)

        }
        popularRecyclerView.adapter=popularMoviesAdapter
    }

    private fun setUpObserver() {
        discoverViewModel.getPopularMovies().observe(viewLifecycleOwner, Observer {
            if(it.results.isNotEmpty()) {
                popularMoviesAdapter.swapData(it.results)
            }


        })
    }

}

