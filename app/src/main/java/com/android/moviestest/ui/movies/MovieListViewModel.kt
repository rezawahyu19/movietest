package com.android.moviestest.ui.movies

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.android.moviestest.data.movie.MovieList
import com.android.moviestest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MovieListViewModel : BaseViewModel() {

    var id = MutableLiveData<Int>()
    var requestGenre = MutableLiveData<Response<MovieList>>()

    fun attachViewModel(lifecycleOwner: LifecycleOwner, listener: MovieListListener) {
        requestGenre.observe(lifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { listener.onSuccessGetMovieList(it) }
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

    interface MovieListListener : BaseListener {

        fun onSuccessGetMovieList(data: MovieList)
    }

    fun getMovieList(with_genre: Int) {
        viewModelScope.launch(coroutineContext) {
            withContext(Dispatchers.Main) {
                val response = retrofitService.getMovieListByGenre(with_genre)
                requestGenre.value = response
            }
        }
    }
}