package com.android.moviestest.ui.detailmovie

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.android.moviestest.data.movie.MovieDetail
import com.android.moviestest.data.review.ReviewList
import com.android.moviestest.data.trailer.TrailerList
import com.android.moviestest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieDetailViewModel : BaseViewModel() {

    var id = MutableLiveData<Int>()
    private var requestMovieByGenre = MutableLiveData<Response<MovieDetail>>()
    private var requestReviewListMovieById = MutableLiveData<Response<ReviewList>>()
    private var requestTrailerListMovieById = MutableLiveData<Response<TrailerList>>()

    fun attachViewModel(lifecycleOwner: LifecycleOwner, listener: MovieDetailListener) {
        requestMovieByGenre.observe(lifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { listener.onSuccessGetMovieById(it) }
            } else {
                val errorResponse = errorResponse(response.errorBody())
                if (errorResponse != null) {
                    listener.onError(errorResponse)
                } else {
                    listener.onFailure("failure")
                }
            }
        })
        requestReviewListMovieById.observe(lifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { listener.onSuccessGetReviewListMovieById(it) }
            } else {
                val errorResponse = errorResponse(response.errorBody())
                if (errorResponse != null) {
                    listener.onError(errorResponse)
                } else {
                    listener.onFailure("failure")
                }
            }
        })
        requestTrailerListMovieById.observe(lifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { listener.onSuccessGetTrailerListMovieById(it) }
            } else {
                val errorResponse = errorResponse(response.errorBody())
                if (errorResponse != null) {
                    listener.onError(errorResponse)
                } else {
                    listener.onFailure("failure")
                }
            }
        })
    }

    interface MovieDetailListener : BaseListener {

        fun onSuccessGetMovieById(data: MovieDetail)
        fun onSuccessGetReviewListMovieById(data: ReviewList)
        fun onSuccessGetTrailerListMovieById(data: TrailerList)
    }

    fun getMovieById(id: Int) {
        viewModelScope.launch(coroutineContext) {
            withContext(Dispatchers.Main) {
                val response = retrofitService.getMovieById(id)
                requestMovieByGenre.value = response
            }
        }
    }

    fun getReviewListMovieById(id: Int) {
        viewModelScope.launch(coroutineContext) {
            withContext(Dispatchers.Main) {
                val response = retrofitService.getReviewListMovieById(id)
                requestReviewListMovieById.value = response
            }
        }
    }

    fun getTrailerListMovieById(id: Int) {
        viewModelScope.launch(coroutineContext) {
            withContext(Dispatchers.Main) {
                val response = retrofitService.getTrailerListMovieById(id)
                requestTrailerListMovieById.value = response
            }
        }
    }
}