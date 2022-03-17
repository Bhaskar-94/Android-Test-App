package com.example.wiprotestapplication.data.network

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

/**
 * RetrofitService interface helps to store all api methods as annotation
 *
 */
interface RetrofitService {
    @GET(ApiConstants.FACTS)
    suspend fun getItemsList(): Response<ResponseBody>
}