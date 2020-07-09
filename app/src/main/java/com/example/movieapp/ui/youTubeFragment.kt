package com.example.movieapp.ui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.codewaves.youtubethumbnailview.ThumbnailLoader
import com.codewaves.youtubethumbnailview.ThumbnailLoadingListener
import com.codewaves.youtubethumbnailview.downloader.OembedVideoInfoDownloader
import com.example.movieapp.R
import com.example.movieapp.common.YOUTUBE_API_KEY
import com.example.movieapp.common.YOUTUBE_BASE_URL
import com.example.movieapp.model.trailer.ResultsItem
import com.example.movieapp.model.trailer.TrailerResponse
import com.example.movieapp.ui.Details.DetailsViewModel
import com.example.movieapp.ui.Details.TrailerAdapter
import com.example.movieapp.utils.FullScreenHelper
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.YouTubeThumbnailView
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerFullScreenListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerListener
import kotlinx.android.synthetic.main.fragment_you_tube.*
import kotlinx.android.synthetic.main.layout_trailer_item.*
import kotlinx.android.synthetic.main.layout_trailer_item.view.*

class youTubeFragment : Fragment() {

    private lateinit var trailerAdapter: TrailerAdapter
    private lateinit var fullScreenHelper: FullScreenHelper
    private lateinit var activity: Activity
    private lateinit var data: MutableList<ResultsItem>
    private lateinit var youTubeViewModel: YouTubeViewModel
    private lateinit var youTubePlayerView: YouTubePlayerView
    val argument: youTubeFragmentArgs by navArgs()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        youTubeViewModel =
            ViewModelProvider(this).get(YouTubeViewModel::class.java)
        return inflater.inflate(R.layout.fragment_you_tube, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

     //   setUpObservers()
        setUpData()

   //     setUpOtherVideo()
    }

  /*  private fun setUpOtherVideo() {
        trailerAdapter = TrailerAdapter { view, resultsItem, i ->
            data.remove(resultsItem)
            if (data.size > 0) {
                noResultFound.visibility = View.GONE
                videoPlayerRecyclerView.visibility = View.VISIBLE
                trailerAdapter.swapData(data)
            } else {
                noResultFound.visibility = View.VISIBLE
                videoPlayerRecyclerView.visibility = View.GONE
            }
        }

        videoPlayerRecyclerView.adapter = trailerAdapter
    }*/

    private fun setUpData() {
        //   ThumbnailLoader.initialize(YOUTUBE_API_KEY)
        progressBar.indeterminateDrawable.colorFilter.run { 0XFFFFFF;PorterDuff.Mode.MULTIPLY }


        val title: String = argument.title
        val urlKey: String = argument.url

        playVideoTitle.text = title


        ThumbnailLoader.initialize()
            .setVideoInfoDownloader(OembedVideoInfoDownloader())
        videoThumbnailView.loadThumbnail(YOUTUBE_BASE_URL + urlKey)

        videoThumbnailView.loadThumbnail(YOUTUBE_BASE_URL + urlKey)

        videoPlayerView.enableAutomaticInitialization = false
        videoPlayerView.initialize(object : AbstractYouTubePlayerListener() {


            override fun onReady(youTubePlayer: YouTubePlayer) {
                videoThumbnailView.visibility = View.GONE
                progressBar.visibility = View.GONE
                videoPlayerView.visibility = View.VISIBLE
                if (lifecycle.currentState == Lifecycle.State.RESUMED) {
                    youTubePlayer.loadVideo(YOUTUBE_BASE_URL + urlKey, 0f)

                } else {
                    youTubePlayer.cueVideo(YOUTUBE_BASE_URL + urlKey, 0f)
                }

            }


        })


        //  videoPlayerRecyclerView.adapter = trailerAdapter

        videoPlayerView.addFullScreenListener(object : YouTubePlayerFullScreenListener {
            @SuppressLint("SourceLockedOrientationActivity")
            override fun onYouTubePlayerEnterFullScreen() {

                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                fullScreenHelper.enterFullScreen()
            }

            @SuppressLint("SourceLockedOrientationActivity")
            override fun onYouTubePlayerExitFullScreen() {
                activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                fullScreenHelper.enterFullScreen()

            }

        })


    }


  /*  private fun setUpObservers() {


        youTubeViewModel.getMoviesTrailer(argument.movieId).observe(viewLifecycleOwner, Observer {
            setUpTrailers(it)


        })
    }

    private fun setUpTrailers(data: TrailerResponse?) {
        data?.let {
            trailerAdapter.swapData(it.results)

        }


    }*/
}