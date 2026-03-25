package com.example.ordermoapplication.utils

import android.widget.EditText
import java.util.regex.Pattern

object ValidationUtils {

    fun isValidEmail(email: String): Boolean {
        val emailPattern = Pattern.compile(
            "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        )
        return emailPattern.matcher(email).matches()
    }

    fun isValidPassword(password: String): Boolean {
        return password.length >= 6
    }

    fun isValidPhone(phone: String): Boolean {
        return phone.length >= 10 && phone.all { it.isDigit() }
    }

    fun isValidName(name: String): Boolean {
        return name.isNotEmpty() && name.length >= 2
    }

    fun validateField(editText: EditText, validator: (String) -> Boolean, errorMessage: String): Boolean {
        val text = editText.text.toString().trim()
        return if (validator(text)) {
            editText.error = null
            true
        } else {
            editText.error = errorMessage
            false
        }
    }
}