#include <jni.h>

JNIEXPORT jstring JNICALL
Java_com_android_moviestest_util_JNIUtil_getApiUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "https://api.themoviedb.org/3/");
}

JNIEXPORT jstring JNICALL
Java_com_android_moviestest_util_JNIUtil_getMediaUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "http://image.tmdb.org/t/p/w185/");
}

JNIEXPORT jstring JNICALL
Java_com_android_moviestest_util_JNIUtil_getYoutubeUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "https://www.youtube.com/watch?v=");
}

JNIEXPORT jstring JNICALL
Java_com_android_moviestest_util_JNIUtil_getYoutubeImageUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "https://img.youtube.com/vi/");
}

JNIEXPORT jstring JNICALL
Java_com_android_moviestest_util_JNIUtil_getYoutubeImageTypeUrl(JNIEnv *env, jobject instance) {
    return (*env)->NewStringUTF(env, "/default.jpg");
}