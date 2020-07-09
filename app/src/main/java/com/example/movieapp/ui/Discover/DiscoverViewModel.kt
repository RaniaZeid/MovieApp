package com.example.movieapp.ui.Discover

import androidx.lifecycle.ViewModel
import com.example.movieapp.Repo.MovieRepository
import com.example.movieapp.common.API_KEY

class DiscoverViewModel : ViewModel() {

    val movieRepository=MovieRepository()

    fun getPopularMovies()=movieRepository.getPopularMovies(API_KEY)
}