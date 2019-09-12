package com.android.moviestest.data.review

import com.android.moviestest.api.ApiPagingResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReviewList : ApiPagingResponse() {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("results")
    @Expose
    var results: List<Review>? = null
}
