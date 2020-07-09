package com.example.movieapp.ui.Favorites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.movieapp.Repo.FavoriteRepository

class FavoritesViewModel : ViewModel() {
    val favoriteRepository=FavoriteRepository()

   fun addFavoriteMovies(Title:String)=favoriteRepository.addFavoriteMovies(Title)
    fun getFavoriteMovies()=favoriteRepository.getMoviesName()
}