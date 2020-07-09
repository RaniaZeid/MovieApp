package com.example.movieapp.Repo

import androidx.lifecycle.MutableLiveData
import com.example.movieapp.common.API_KEY
import com.example.movieapp.data.remote.ApiManager
import com.example.movieapp.model.Cast.CastResponse
import com.example.movieapp.model.details.DetailsResponse
import com.example.movieapp.model.popular.PopularResponse
import com.example.movieapp.model.trailer.TrailerResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MovieRepository {

    fun getPopularMovies(api_key:String):MutableLiveData<PopularResponse> {
        val popularLiveData= MutableLiveData<PopularResponse>()

        ApiManager.apiServices.getPopularMovie(API_KEY, page = 1)
            .enqueue(object : Callback<PopularResponse> {
                override fun onFailure(call: Call<PopularResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<PopularResponse>,
                    response: Response<PopularResponse>) {
                    if (response.isSuccessful){
                        response.body().let {
                            popularLiveData.postValue(it)
                        }
                    }
                }

            })

return popularLiveData
    }
    fun getMoviesDetails(movieId:Int):MutableLiveData<DetailsResponse> {
        val detailsLiveData= MutableLiveData<DetailsResponse>()

        ApiManager.apiServices.getMoviesDetails(movieId , API_KEY)
            .enqueue(object : Callback<DetailsResponse> {
                override fun onFailure(call: Call<DetailsResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<DetailsResponse>,
                    response: Response<DetailsResponse>
                ) {
                    if (response.isSuccessful){
                        response.body().let {
                            detailsLiveData.postValue(it)
                        }
                    }
                }

            })
        return detailsLiveData



    }

    fun getMoviesCast(movieId:Int):MutableLiveData<CastResponse>
    {
        val castLiveData= MutableLiveData<CastResponse>()

        ApiManager.apiServices.getMoviesCast(movieId , API_KEY)
            .enqueue(object : Callback<CastResponse> {
                override fun onFailure(call: Call<CastResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<CastResponse>,
                    response: Response<CastResponse>
                ) {
                    if (response.isSuccessful){
                        response.body().let {
                            castLiveData.postValue(it)
                        }
                    }
                }

            })
        return castLiveData



    }
    fun getMoviesTrailer(movieId:Int):MutableLiveData<TrailerResponse>
    {
        val trailerLiveData= MutableLiveData<TrailerResponse>()

        ApiManager.apiServices.getMoviesTrailer(movieId , API_KEY)
            .enqueue(object : Callback<TrailerResponse> {
                override fun onFailure(call: Call<TrailerResponse>, t: Throwable) {

                }

                override fun onResponse(
                    call: Call<TrailerResponse>,
                    response: Response<TrailerResponse>
                ) {
                    if (response.isSuccessful){
                        response.body().let {
                            trailerLiveData.postValue(it)
                        }
                    }
                }

            })
        return trailerLiveData



    }

}