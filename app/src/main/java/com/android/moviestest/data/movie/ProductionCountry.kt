package com.android.moviestest.data.movie

import com.android.moviestest.api.ApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCountry : ApiResponse() {

    @SerializedName("iso_3166_1")
    @Expose
    var iso31661: String? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
}