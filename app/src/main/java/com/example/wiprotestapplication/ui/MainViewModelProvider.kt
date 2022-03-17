package com.example.wiprotestapplication.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.wiprotestapplication.data.repositories.MainRepository

/**
 * MainViewModelProvider class child of ViewModelProvider Factory class
 * It's helps to send objects from View to its View model as a parameter
 */
class MainViewModelProvider(var repository: MainRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}