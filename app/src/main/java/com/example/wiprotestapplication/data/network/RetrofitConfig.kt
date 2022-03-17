package com.example.wiprotestapplication.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * RetrofitConfig class helps to create retrofit object
 * along with it's set up all the configuration
 */
class RetrofitConfig {
    companion object {
        /**
         * Return a retrofit object
         */
        private fun getRetrofitInstance(): Retrofit {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .addInterceptor(interceptor).build()

            return Retrofit.Builder()
                .client(client)
                .baseUrl(ApiConstants.BASE_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        /**
         * Return a generic object of Retrofit class
         */
        fun <T> buildService(serviceType: Class<T>): T {
            return getRetrofitInstance().create(serviceType)
        }
    }
}