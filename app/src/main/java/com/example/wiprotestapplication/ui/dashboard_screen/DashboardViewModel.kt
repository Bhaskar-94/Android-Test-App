package com.example.wiprotestapplication.ui.dashboard_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wiprotestapplication.data.repositories.MainRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * MainViewModel class is a child class of ViewModel class
 * It is tied with the lifecycle of It's view
 */
class DashboardViewModel(private val repository: MainRepository) : ViewModel() {

    private val _progressStatus: MutableLiveData<Boolean> = MutableLiveData()
    val progressStatusData: LiveData<Boolean>
        get() = _progressStatus
    private val _itemsList: MutableLiveData<MainModel> = MutableLiveData()
    val itemsListData: LiveData<MainModel>
        get() = _itemsList

    // it start automatically after create an object od MainViewModel
    init {
        getListItems()
    }

    /**
     * This Function create a local launch method in a different thread for background api call
     * It's tied with Coroutine Scope
     * helps to call suspend functions
     */
    fun getListItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _progressStatus.postValue(true)
                val mainModel: MainModel = repository.getListItems()
                _progressStatus.postValue(false)
                _itemsList.postValue(mainModel)
            } catch (t: Throwable) {
                _progressStatus.postValue(false)
                t.printStackTrace()
            }
        }
    }

}