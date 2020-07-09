package com.example.movieapp.ui.Details

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.codewaves.youtubethumbnailview.ThumbnailLoader
import com.codewaves.youtubethumbnailview.ThumbnailLoadingListener
import com.codewaves.youtubethumbnailview.downloader.OembedVideoInfoDownloader
import com.example.movieapp.R
import com.example.movieapp.common.YOUTUBE_BASE_URL
import com.example.movieapp.model.trailer.ResultsItem
import kotlinx.android.synthetic.main.layout_trailer_item.view.*
import java.util.*


private lateinit var activity: Activity
private lateinit var context: Context

class TrailerAdapter(val listener: (View, ResultsItem, Int) -> Unit) :
    RecyclerView.Adapter<TrailerAdapter.TrailerViewModel>() {

    private var data: List<ResultsItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrailerViewModel {
        return TrailerViewModel(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_trailer_item, parent, false)
        )
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: TrailerViewModel, position: Int) =
        holder.bind(data[position])

    fun swapData(data: List<ResultsItem>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class TrailerViewModel(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(item: ResultsItem) = with(itemView) {
            var videoUrl: String = YOUTUBE_BASE_URL + item.key
            videoTitle.text = item.name
           // setThumbnail()
            ThumbnailLoader.initialize()
                .setVideoInfoDownloader(OembedVideoInfoDownloader())
            videoImageView.loadThumbnail(videoUrl)

            videoImageView.loadThumbnail(videoUrl, object : ThumbnailLoadingListener {

                override fun onLoadingComplete(url: String, view: View) {
                    //  videoImageView.loadThumbnail(videoUrl)
                    Toast.makeText(context, "complete", Toast.LENGTH_SHORT).show()
                }

                override fun onLoadingStarted(url: String, view: View) {
                }

                override fun onLoadingCanceled(url: String, view: View) {
                }

                override fun onLoadingFailed(url: String, view: View, error: Throwable?) {
                    Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
                }

            })

          setOnClickListener {
                listener.invoke(it, item, adapterPosition)

            }

        }
    }


}


