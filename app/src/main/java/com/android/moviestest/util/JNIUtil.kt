package com.android.moviestest.util


object JNIUtil {

    init {
        System.loadLibrary("constant")
    }


    val apiURL: String
        get() {
            return getApiUrl()
        }

    val mediaURL: String
        get() {
            return getMediaUrl()
        }

    val youtubeURL: String
        get() {
            return getYoutubeUrl()
        }

    val youtubeImageURL: String
        get() {
            return getYoutubeImageUrl()
        }

    val youtubeImageTypeURL: String
        get() {
            return getYoutubeImageTypeUrl()
        }

    private external fun getApiUrl(): String
    private external fun getMediaUrl(): String
    private external fun getYoutubeUrl(): String
    private external fun getYoutubeImageUrl(): String
    private external fun getYoutubeImageTypeUrl(): String
}
