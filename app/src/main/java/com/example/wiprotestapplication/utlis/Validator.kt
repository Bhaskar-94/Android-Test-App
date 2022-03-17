package com.example.wiprotestapplication.utlis

object Validator {

    /**
     * remove all null object from list
     */
    fun validateList(rows: List<Any>): Boolean {
        return rows.isNotEmpty()
    }
}