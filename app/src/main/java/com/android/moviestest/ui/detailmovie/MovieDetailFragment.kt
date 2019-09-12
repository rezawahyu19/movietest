package com.android.moviestest.ui.detailmovie

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.moviestest.R
import com.android.moviestest.api.ApiResponse
import com.android.moviestest.data.movie.MovieDetail
import com.android.moviestest.data.review.ReviewList
import com.android.moviestest.data.trailer.TrailerList
import com.android.moviestest.data.trailer.Youtube
import com.android.moviestest.ui.base.BaseFragment
import com.android.moviestest.util.Utils.setImageURI
import com.android.moviestest.util.JNIUtil
import kotlinx.android.synthetic.main.fragment_movie_detail.*
import kotlinx.android.synthetic.main.layout_error.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MovieDetailFragment : BaseFragment(), MovieDetailViewModel.MovieDetailListener {

    private var adapter: YoutubeListAdapter? = null
    private val viewModel: MovieDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_movie_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.attachViewModel(this, this)

        if (viewModel.id.value == null) {
            viewModel.id.value = arguments?.getInt("id")
        }

        adapter = YoutubeListAdapter(mutableListOf(), object : YoutubeListAdapter.Listener {
            override fun onItemClick(itemData: Youtube) {
                activity?.startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(JNIUtil.youtubeURL + itemData.source)
                    )
                )
            }
        })
        recyclerView.adapter = adapter

        viewModel.id.value?.let {
            viewModel.getMovieById(it)
            viewModel.getTrailerListMovieById(it)
        }

        swipeRefreshLayout.setOnRefreshListener {
            viewModel.id.value?.let {
                viewModel.getMovieById(it)
                viewModel.getTrailerListMovieById(it)
            }
        }
    }

    override fun onFailure(message: String) {
        viewAnimator.displayedChild = VIEW_ERROR
        swipeRefreshLayout.isRefreshing = false
        tvError.text = message
    }

    override fun onError(error: ApiResponse) {
        viewAnimator.displayedChild = VIEW_ERROR
        swipeRefreshLayout.isRefreshing = false
        tvError.text = error.statusMessage
    }

    override fun onSuccessGetMovieById(data: MovieDetail) {
        viewAnimator.displayedChild = VIEW_SUCCESS
        swipeRefreshLayout.isRefreshing = false
        ivImage.setImageURI(JNIUtil.mediaURL + data.posterPath)
        tvName.text = data.title
        tvRelease.text = data.releaseDate
        tvVote.text = data.voteAverage.toString()
        tvOverview.text = data.overview
    }

    override fun onSuccessGetReviewListMovieById(data: ReviewList) {

    }


    override fun onSuccessGetTrailerListMovieById(data: TrailerList) {
        viewAnimator.displayedChild = VIEW_SUCCESS
        swipeRefreshLayout.isRefreshing = false
        data.youtube?.toMutableList()?.let {
            adapter?.data = it
            adapter?.notifyDataSetChanged()
        }
    }
}
