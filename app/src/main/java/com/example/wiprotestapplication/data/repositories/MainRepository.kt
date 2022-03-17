package com.example.wiprotestapplication.data.repositories

import android.util.Log
import com.example.wiprotestapplication.data.network.RetrofitService
import okhttp3.ResponseBody
import org.json.JSONObject

/**
 * Repository class takes RetrofitService class object as parameter
 * It's helps to get response data
 */
class MainRepository(private val retrofitService: RetrofitService) {
    private val TAG: String = "Main Repository"

    /**
     * This function is a suspended function which return the response body
     */
    suspend fun getListItems(): ResponseBody {
        val response = retrofitService.getItemsList()
        if (response.isSuccessful) {
            response.raw().cacheResponse?.body?.let {
                return it
            }
            response.raw().networkResponse?.body?.let {
                return it
            }
            return response.body()!!
        } else {
            val message = StringBuilder()
            val errorResponse = response.errorBody()?.toString()
            Log.d(TAG, "handleResponse Error: $errorResponse")
            Log.d(TAG, "handleResponse Response Code: ${response.code()}")
            message.append(response.code())
            message.append("%")
            try {
                if (errorResponse != null) {
                    val jsonObject = JSONObject(errorResponse)
                    val errorPayload = jsonObject.getString("payload")
                    val jsonErrorObject = JSONObject(errorPayload)
                    val errorMsg = jsonErrorObject.getString("message")
                    Log.e(TAG, "--errorMsg--: $errorMsg")
                    message.append(errorMsg)
                }
            } catch (e: Exception) {
                message.append(response.message())
            }
            throw Exception(message.toString())
        }
    }
}