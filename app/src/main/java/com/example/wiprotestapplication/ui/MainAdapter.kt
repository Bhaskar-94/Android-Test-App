package com.example.wiprotestapplication.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import com.example.wiprotestapplication.R
import com.example.wiprotestapplication.databinding.RowCountryInfoBinding
import com.example.wiprotestapplication.ui.MainAdapter.ViewHolder

/**
 * MainAdapter class extends RecyclerView adapter class
 *
 */
class MainAdapter(
    private val context: Context,
    private val countryInfoList: ArrayList<CountryInfo>
) :
    RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.row_country_info, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(countryInfoList[position], context)
    }

    override fun getItemCount(): Int {
        return countryInfoList.size
    }

    /**
     * Class takes a view as parameter
     * extends Recyclerview ViewHolder class and pass view as parameter
     */
    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding = RowCountryInfoBinding.bind(view)  //bind the view

        fun bind(countryInfo: CountryInfo, context: Context) {
            binding.tvTitle.text = countryInfo.title
            binding.tvDescription.text = countryInfo.description
            try {
                Glide.with(context).load(countryInfo.imageHref)
                    .apply(RequestOptions.placeholderOf(android.R.drawable.stat_notify_error))
                    .error(android.R.drawable.stat_notify_error)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(binding.ivUser)

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /**
     * Function takes a list of objects
     * and update local list and refresh adapter
     */
    fun updateData(mCountryInfoList: ArrayList<CountryInfo>) {
        countryInfoList.clear()
        countryInfoList.addAll(mCountryInfoList)
        notifyDataSetChanged()
    }
}