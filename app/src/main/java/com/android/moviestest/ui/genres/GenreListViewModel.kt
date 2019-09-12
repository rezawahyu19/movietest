package com.android.moviestest.ui.genres

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import com.android.moviestest.data.genre.GenreList
import com.android.moviestest.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class GenreListViewModel : BaseViewModel() {

    var requestGenre = MutableLiveData<Response<GenreList>>()

    fun attachViewModel(lifecycleOwner: LifecycleOwner, listener: GenreListListener) {
        requestGenre.observe(lifecycleOwner, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.let { listener.onSuccessGetGenreList(it) }
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

    interface GenreListListener : BaseListener {

        fun onSuccessGetGenreList(data: GenreList)
    }

    fun getGenreList() {
        viewModelScope.launch(coroutineContext) {
            withContext(Dispatchers.Main) {
                val response = retrofitService.getGenreList()
                requestGenre.value = response
            }
        }
    }
}