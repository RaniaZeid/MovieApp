package com.example.movieapp.ui.Favorites

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import kotlinx.android.synthetic.main.layout_movies_name_item.view.*
import java.util.*

class FavoritesAdapter(val listener: (View, MoviesName, Int) -> Unit) :
    RecyclerView.Adapter<FavoritesAdapter.MoviesNameViewHolder>() {

    private var data: List<MoviesName> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesNameViewHolder {
        return MoviesNameViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_movies_name_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: MoviesNameViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<MoviesName>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class MoviesNameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: MoviesName) = with(itemView) {
            favoriteMovieName.text=item.name
            setOnClickListener {
                listener.invoke(it, item, adapterPosition)
            }
        }
    }
}