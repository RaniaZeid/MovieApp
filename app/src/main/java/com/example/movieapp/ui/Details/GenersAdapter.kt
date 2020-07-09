package com.example.movieapp.ui.Details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapp.R
import com.example.movieapp.model.details.GenresItem
import kotlinx.android.synthetic.main.popular_movies_item.view.*
import java.util.*

class GenersAdapter(val listener: (View, GenresItem, Int) -> Unit) :
    RecyclerView.Adapter<GenersAdapter.GenersViewHolder>() {

    private var data: List<GenresItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenersViewHolder {
        return GenersViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_geners_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: GenersViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<GenresItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class GenersViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: GenresItem) = with(itemView) {
            nameTextView.text =item.name
            setOnClickListener {
                listener.invoke(it, item, adapterPosition)
            }
        }
    }
}