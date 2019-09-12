package com.android.moviestest.data.trailer

import com.android.moviestest.api.ApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Youtube : ApiResponse() {

    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("size")
    @Expose
    var size: String? = null
    @SerializedName("source")
    @Expose
    var source: String? = null
    @SerializedName("type")
    @Expose
    var type: String? = null
}