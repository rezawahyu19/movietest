package com.android.moviestest.data.genre

import com.android.moviestest.api.ApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenreList : ApiResponse() {

    @SerializedName("genres")
    @Expose
    var genres: List<Genre>? = null
}
