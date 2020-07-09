package com.example.movieapp.ui

import androidx.lifecycle.ViewModel
import com.example.movieapp.Repo.MovieRepository

class YouTubeViewModel : ViewModel() {
val movieRepository=MovieRepository()
    fun getMoviesTrailer(movieId:Int)=movieRepository.getMoviesTrailer(movieId)

}
