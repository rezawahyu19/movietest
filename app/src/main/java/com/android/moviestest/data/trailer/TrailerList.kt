package com.android.moviestest.data.trailer

import com.android.moviestest.api.ApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class TrailerList : ApiResponse() {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("quicktime")
    @Expose
    var quicktime: List<Any>? = null
    @SerializedName("youtube")
    @Expose
    var youtube: List<Youtube>? = null
}