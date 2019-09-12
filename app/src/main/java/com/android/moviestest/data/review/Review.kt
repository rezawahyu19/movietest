package com.android.moviestest.data.review

import com.android.moviestest.api.ApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class Review : ApiResponse() {

    @SerializedName("author")
    @Expose
    var author: String? = null
    @SerializedName("content")
    @Expose
    var content: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("url")
    @Expose
    var url: String? = null
}