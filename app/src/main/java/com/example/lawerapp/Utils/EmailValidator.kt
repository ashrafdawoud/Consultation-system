package com.example.lawerapp.Utils

import java.util.regex.Matcher
import java.util.regex.Pattern

public class EmailValidator {
    public fun checkEmailForValidity(email: String?): Boolean {
        val matcher: Matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email)
        return matcher.find()
    }
    private val VALID_EMAIL_ADDRESS_REGEX: Pattern =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE)

}