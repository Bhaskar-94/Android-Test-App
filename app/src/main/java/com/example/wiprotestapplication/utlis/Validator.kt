package com.example.wiprotestapplication.utlis

object Validator {

    /**
     * check the input is null or not
     */
    fun validateList(rows: List<Any>): Boolean {
        return rows.isNotEmpty()
    }
}