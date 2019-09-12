package com.android.moviestest.ui.base

import androidx.lifecycle.ViewModel
import com.android.moviestest.api.ApiResponse
import com.android.moviestest.api.RetrofitFactory
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import okhttp3.ResponseBody

open class BaseViewModel : ViewModel() {

    val gson = Gson()
    val coroutineJob = SupervisorJob()
    val coroutineContext = Dispatchers.IO + coroutineJob
    val retrofitService by lazy { RetrofitFactory.retrofitService() }

    fun errorResponse(errorBody: ResponseBody?): ApiResponse? {
        return gson.fromJson(errorBody?.string(), ApiResponse::class.java)
    }

    interface BaseListener {

        fun onLoading(isLoading: Boolean)
        fun onError(error: ApiResponse)
        fun onFailure(message: String)
    }
}
