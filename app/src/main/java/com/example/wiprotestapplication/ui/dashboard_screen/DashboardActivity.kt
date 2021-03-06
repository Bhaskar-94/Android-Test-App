package com.example.wiprotestapplication.ui.dashboard_screen

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.wiprotestapplication.R
import com.example.wiprotestapplication.data.network.RetrofitConfig
import com.example.wiprotestapplication.data.network.RetrofitService
import com.example.wiprotestapplication.data.repositories.MainRepository
import com.example.wiprotestapplication.databinding.ActivityMainBinding
import com.example.wiprotestapplication.utlis.Validator

/**
 * MainActivity class is the launching view
 *
 */
class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DashboardViewModel
    private var countryInfoList = ArrayList<CountryInfo>()
    private lateinit var adapter: DashboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)    //Attached binding view to Activity
        initViews()
    }

    override fun onResume() {
        super.onResume()
        clickListeners()
        createObserver()
    }

    /**
     * Helps to initialize objects and attached view model with activity
     * Set adapter with Recycler view
     */
    private fun initViews() {
        val retrofitService: RetrofitService =
            RetrofitConfig.buildService(RetrofitService::class.java)
        val repository = MainRepository(retrofitService)
        val factory = DashboardViewModelProvider(repository)
        viewModel = ViewModelProvider(this, factory)[DashboardViewModel::class.java]

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvItemList.layoutManager = layoutManager
        adapter = DashboardAdapter(this, countryInfoList)
        binding.rvItemList.adapter = adapter

        binding.swipeToRefresh.setProgressBackgroundColorSchemeColor(
            ContextCompat.getColor(
                this,
                R.color.grey
            )
        )
        binding.swipeToRefresh.setColorSchemeColors(Color.WHITE)
    }

    /**
     * Helps to write all click listeners
     */
    private fun clickListeners() {
        binding.swipeToRefresh.setOnRefreshListener {
            viewModel.getListItems()
        }
    }

    /**
     * separate all observer in this function
     */
    private fun createObserver() {
        viewModel.progressStatusData.observe(this) {
            if (it) {
                binding.swipeToRefresh.isRefreshing = it
            } else
                binding.swipeToRefresh.isRefreshing = it
        }

        viewModel.itemsListData.observe(this) {
            binding.toolbar.title = it.title
            val filteredList = modifyList(it.rows)
            adapter.updateData(filteredList as ArrayList<CountryInfo>)
        }
    }

    /**
     * remove all null object from list
     */
    private fun modifyList(rows: List<CountryInfo>): List<CountryInfo> {
        if (Validator.validateList(rows)) {
            return rows.filter { countryInfo ->
                (countryInfo.title.isNullOrEmpty()).not()
            }
        }
        return listOf<CountryInfo>()
    }
}