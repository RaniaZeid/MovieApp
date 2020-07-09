package com.example.movieapp.Repo

import android.os.Build.ID
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.movieapp.ui.Favorites.MoviesName
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FavoriteRepository {
   fun addFavoriteMovies(Title:String) {
        // Add a new document with a generated id.
        val data = hashMapOf(
            "name" to Title
             //  ID to movieId
        )

        Firebase.firestore.collection("Movies")
            .add(data)
            .addOnSuccessListener { documentReference ->
                Log.d("TAG", "DocumentSnapshot written with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w("TAG", "Error adding document", e)
            }
    }

  fun getMoviesName() :MutableLiveData<List<MoviesName>>{
      val moviesFavoritesName=MutableLiveData<List<MoviesName>>()
        Firebase.firestore.collection("Movies")
            .addSnapshotListener { value, e ->
                if (e != null) {
                    Log.w("TAG", "Listen failed.", e)
                    return@addSnapshotListener
                }

                val rooms = ArrayList<MoviesName>()
                for (doc in value!!) {
                    rooms.add(
                        MoviesName(
                            doc.getString("name")!!

                        )
                    )
                }
                moviesFavoritesName.postValue(rooms)
                Log.d("TAG", "Current cites in CA: $rooms")
            }
      return moviesFavoritesName
    }
}