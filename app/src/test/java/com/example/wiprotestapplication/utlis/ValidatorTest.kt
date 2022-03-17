package com.example.wiprotestapplication.utlis

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class ValidatorTest{

    @Test
    fun whenInputIsValid(){
        val testList = listOf<Any>('a','b','c')
        val testResult = Validator.validateList(testList)
        assertThat(testResult).isEqualTo(true)
    }

    @Test
    fun whenInputIsNotValid(){
        val testList = listOf<Any>()
        val testResult = Validator.validateList(testList)
        assertThat(testResult).isEqualTo(false)
    }
}