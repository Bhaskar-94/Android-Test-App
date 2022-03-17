package com.example.wiprotestapplication.ui

/**
 * MainModel is a data class to store data
 *
 */
data class MainModel(
    val rows: List<CountryInfo>,
    val title: String
)

data class CountryInfo(
    val description: String?,
    val imageHref: String,
    val title: String?
)