package com.example.wiprotestapplication.data.network

import android.util.Log
import org.json.JSONObject
import retrofit2.Response

open class SafeApiRequest {
    suspend fun <T : Any> apiRequest(call: suspend () -> Response<T>): T {
        val response = call.invoke()
        if (response.isSuccessful) {
            response.raw().cacheResponse?.body?.let {
                return it as T
            }

            response.raw().networkResponse?.body?.let {
                return it as T
            }
            return response.body()!!
        } else {
            val message = StringBuilder()
            val errorResponse = response.errorBody()?.toString()
            Log.d("SafeApiRequest", "handleResponse Error: $errorResponse")
            Log.d("SafeApiRequest", "handleResponse Response Code: ${response.code()}")
            message.append(response.code())
            message.append("%")
            try {
                if (errorResponse != null) {
                    val jsonObject = JSONObject(errorResponse)
                    val errorMsg = jsonObject.getString("message")
                    Log.e("Safe Api Request", "--errorMsg--: $errorMsg")
                    message.append(errorMsg)
                }
            } catch (e: Exception) {
                message.append(response.message())
            }
            throw Exception(message.toString())
        }
    }
}