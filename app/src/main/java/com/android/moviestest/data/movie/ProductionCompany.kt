package com.android.moviestest.data.movie

import com.android.moviestest.api.ApiResponse
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ProductionCompany : ApiResponse() {

    @SerializedName("id")
    @Expose
    var id: Int? = null
    @SerializedName("logo_path")
    @Expose
    var logoPath: Any? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("origin_country")
    @Expose
    var originCountry: String? = null
}