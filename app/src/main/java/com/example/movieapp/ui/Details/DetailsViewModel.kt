package com.example.movieapp.ui.Details

import androidx.lifecycle.ViewModel
import com.example.movieapp.Repo.MovieRepository

class DetailsViewModel : ViewModel() {

    val movieRepository= MovieRepository()

    fun getMoviesDetails(moviesId:Int)=movieRepository.getMoviesDetails(moviesId)
    fun getMoviesCast(movieId:Int)=movieRepository.getMoviesCast(movieId)
    fun getMoviesTrailer(movieId:Int)=movieRepository.getMoviesTrailer(movieId)

}