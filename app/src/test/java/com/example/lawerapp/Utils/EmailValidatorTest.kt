package com.example.lawerapp.Utils

import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test

class EmailValidatorTest{

    @Test
    fun checkEmailForValidity_istrue () {
        val email:String="ashraf@gmail.com"
        Assert.assertTrue(EmailValidator().checkEmailForValidity(email))
    }
    @Test
    fun checkEmailForValidity_isfalse(){
        val email:String="ashraf@gmail"
        Assert.assertFalse(EmailValidator().checkEmailForValidity(email))
    }
}