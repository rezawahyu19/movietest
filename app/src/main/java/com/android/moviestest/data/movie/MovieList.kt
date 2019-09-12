package com.android.moviestest.data.movie

import com.android.moviestest.api.ApiPagingResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class MovieList : ApiPagingResponse() {

    @SerializedName("results")
    @Expose
    var results: List<Movie>? = null
}
