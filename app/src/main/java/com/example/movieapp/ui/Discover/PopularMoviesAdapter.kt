package com.example.movieapp.ui.Discover

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.common.MOVIEDB_IMAGE_BASE
import com.example.movieapp.model.popular.ResultsItem
import kotlinx.android.synthetic.main.popular_movies_item.view.*
import java.util.*

class PopularMoviesAdapter(val listener: (View, ResultsItem, Int) -> Unit) :
    RecyclerView.Adapter<PopularMoviesAdapter.PopularViewHolder>() {

    private var data: List<ResultsItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularViewHolder {
        return PopularViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.popular_movies_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: PopularViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<ResultsItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class PopularViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ResultsItem) = with(itemView) {
            Glide.with(this).load("$MOVIEDB_IMAGE_BASE${item.posterPath}")
                .into(movieImageView)
            nameTextView.text = item.title
            setOnClickListener {
                listener.invoke(it, item, adapterPosition)
            }
        }
    }
}