package com.example.wiprotestapplication.data.repositories

import com.example.wiprotestapplication.data.network.RetrofitService
import com.example.wiprotestapplication.data.network.SafeApiRequest
import com.example.wiprotestapplication.ui.dashboard_screen.MainModel

/**
 * Repository class takes RetrofitService class object as parameter
 * It's helps to get response data
 */
class MainRepository(private val retrofitService: RetrofitService) : SafeApiRequest() {
    /**
     * This function is a suspended function which return the response body
     */
    suspend fun getListItems(): MainModel {
        return apiRequest {
            retrofitService.getItemsList()
        }
    }
}