package com.example.movieapp.ui.Details

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.common.MOVIEDB_IMAGE_BASE
import com.example.movieapp.model.Cast.CastItem
import kotlinx.android.synthetic.main.layout_cast_item.view.*
import java.util.*

class CastAdapter(val listener: (View, CastItem, Int) -> Unit) :
    RecyclerView.Adapter<CastAdapter.castViewHolder>() {

    private var data: List<CastItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): castViewHolder {
        return castViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_cast_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: castViewHolder, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<CastItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class castViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: CastItem) = with(itemView) {
            if(item.profilePath!=null) {

                Glide.with(this).load("$MOVIEDB_IMAGE_BASE${item.profilePath}").into(castImageView)
                castNameTextView.text = item.name
            }
            setOnClickListener {
                listener.invoke(it, item, adapterPosition)
            }
        }
    }
}