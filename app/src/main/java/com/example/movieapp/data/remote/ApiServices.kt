package com.example.movieapp.data.remote

import com.example.movieapp.model.Cast.CastResponse
import com.example.movieapp.model.details.DetailsResponse
import com.example.movieapp.model.popular.PopularResponse
import com.example.movieapp.model.trailer.TrailerResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServices {
    @GET("movie/popular")
    fun getPopularMovie(
        @Query("api_key") api_key: String,
        @Query("language") language: String? = null,
        @Query("page") page: Int? = null,
        @Query("region") region: String? = null
    ): Call<PopularResponse>

    @GET("movie/{movie_id}")
    fun getMoviesDetails(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ):Call<DetailsResponse>

    @GET("movie/{movie_id}/credits")
    fun getMoviesCast(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ):Call<CastResponse>
    @GET("movie/{movie_id}/videos")
    fun getMoviesTrailer(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String
    ):Call<TrailerResponse>


}