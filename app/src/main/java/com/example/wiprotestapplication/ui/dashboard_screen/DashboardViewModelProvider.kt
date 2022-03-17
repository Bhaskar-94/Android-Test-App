package com.example.wiprotestapplication.ui.dashboard_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wiprotestapplication.data.repositories.MainRepository

/**
 * MainViewModelProvider class child of ViewModelProvider Factory class
 * It's helps to send objects from View to its View model as a parameter
 */
class DashboardViewModelProvider(var repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DashboardViewModel::class.java)) {
            return DashboardViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}