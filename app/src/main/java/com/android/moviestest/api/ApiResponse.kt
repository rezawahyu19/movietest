package com.android.moviestest.api

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class ApiResponse {

    @SerializedName("status_code")
    @Expose
    var statusCode: Int? = null
    @SerializedName("status_message")
    @Expose
    var statusMessage: String? = null
}
