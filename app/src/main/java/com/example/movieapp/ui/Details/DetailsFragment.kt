package com.example.movieapp.ui.Details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.common.MOVIEDB_IMAGE_BASE
import com.example.movieapp.model.Cast.CastResponse
import com.example.movieapp.model.details.DetailsResponse
import com.example.movieapp.model.trailer.TrailerResponse
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.fragment_details.*
import java.util.*

class DetailsFragment : Fragment() {

    private lateinit var trailerAdapter: TrailerAdapter
    private lateinit var castAdapter: CastAdapter
    private lateinit var genersAdapter: GenersAdapter
    private lateinit var detailsViewModel: DetailsViewModel
   lateinit var youTubePlayer:YouTubePlayerView
var position:Int=0

    val arguments: DetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        detailsViewModel =
            ViewModelProvider(this).get(DetailsViewModel::class.java)
        return inflater.inflate(R.layout.fragment_details, container, false)


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpCastRecyclerView()
        setupTrailersRecyclerView()
        setUpObservers()

    }

    private fun setupTrailersRecyclerView() {
        trailerAdapter=TrailerAdapter{view, resultsItem, i ->

            val title:String=resultsItem.name
            val url:String=resultsItem.key
            val movieId=arguments.movieId
            val Action=DetailsFragmentDirections.actionNavigationDetailsToNavigationYoutube(title,url,movieId)
            findNavController().navigate(Action)


        }
        trailerRecyclerView.adapter=trailerAdapter

    }

    private fun setUpCastRecyclerView() {

        castAdapter=CastAdapter{
            view, castItem, i ->
        }

       val layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
   layoutManager.isAutoMeasureEnabled=true
        castRecyclerView.setHasFixedSize(false)
        castRecyclerView.layoutManager=layoutManager

        castRecyclerView.adapter=castAdapter


    }

    private fun setUpRecyclerView() {
        val layoutManager = FlexboxLayoutManager(context)
        layoutManager.flexDirection = FlexDirection.ROW
        layoutManager.justifyContent = JustifyContent.FLEX_START
        genersAdapter=GenersAdapter{
            view, genresItem, i ->

        }
        genersRecyclerView.layoutManager = layoutManager
        genersRecyclerView.adapter= genersAdapter
    }

    private fun setUpObservers() {
        detailsViewModel.getMoviesDetails(arguments.movieId).observe(viewLifecycleOwner, Observer {
            setUpViews(it)
        })
        detailsViewModel.getMoviesCast(arguments.movieId).observe(viewLifecycleOwner, Observer {
            setupCastView(it)
        })

        detailsViewModel.getMoviesTrailer(arguments.movieId).observe(viewLifecycleOwner, Observer {
            setUpTrailers(it)

        }
        )

    }

    private fun setUpTrailers(TrailerData: TrailerResponse?) {
        TrailerData?.let{
            trailerAdapter.swapData(it.results)



        }


    }

    private fun setupCastView(castData: CastResponse?) {
       castData?.let {
           if (it.cast.isNotEmpty()) {
               castAdapter.swapData(it.cast)
           }
       }

    }

    private fun setUpViews(data: DetailsResponse?) {
        data?.let {
            Glide.with(this).load("$MOVIEDB_IMAGE_BASE${it.posterPath}").into(posterImageView)
            Glide.with(this).load("$MOVIEDB_IMAGE_BASE${it.backdropPath}").into(bannerImageView)

genersAdapter.swapData(it.genres)

        titleTextView.text = it.title
        overviewTextView.text = it.overview
        ratingTextView.text = it.voteAverage.toString()
        languageTextView.text = it.originalLanguage
        releaseTextView.text = it.releaseDate
            favoriteImageView.setOnClickListener {
               val MovieTitle=data.title
                val Action=DetailsFragmentDirections.actionNavigationDetailsToNavigationFavorites(MovieTitle
                )
                findNavController().navigate(Action)
            }



    }

}
}