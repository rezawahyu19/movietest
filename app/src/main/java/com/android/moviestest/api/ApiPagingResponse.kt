package com.android.moviestest.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ApiPagingResponse : ApiResponse() {

    @SerializedName("page")
    @Expose
    var page: Int? = null
    @SerializedName("total_results")
    @Expose
    var totalResults: Int? = null
    @SerializedName("total_pages")
    @Expose
    var totalPages: Int? = null
}
