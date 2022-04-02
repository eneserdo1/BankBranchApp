package com.app.bankbranchapp.common.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.app.bankbranchapp.common.models.Resource
import retrofit2.Response


suspend fun  <T> getResponse(request: suspend () -> Response<T>, defaultErrorMessage: String): Resource<T> {
    return try {
        val result = request.invoke()
        if (result.isSuccessful) {
            return Resource.success(result.body())
        } else {
            Resource.error(defaultErrorMessage, null)
        }

    } catch (e: Exception) {
        e.printStackTrace()
        return Resource.error("Error ${e.message}", null)
    }
}

fun <T> MutableLiveData<T>.toLiveData() : LiveData<T> = this