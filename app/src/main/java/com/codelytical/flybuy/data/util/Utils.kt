package com.codelytical.flybuy.data.util

import com.codelytical.flybuy.data.model.ValidationResult


object Utils {

    fun formatPrice(price : String): String {
        return String.format("%.2f", price.toDouble())
    }

    fun validateLoginRequest(username: String,password: String) : ValidationResult {
        if (username.isBlank() && password.isBlank()) return ValidationResult(false,"Email and password cannot be blank")
        if (username.isBlank()) return ValidationResult(false,"Email cannot be blank")
        if (password.isBlank()) return ValidationResult(false,"Password cannot be blank")
        return ValidationResult(true)
    }

    fun validateRegisterRequest(name: String, email: String, password: String) : ValidationResult {
        if (name.isBlank() && email.isBlank() && password.isBlank()) return ValidationResult(false,"Please all fields are required")
        if (name.isBlank()) return ValidationResult(false,"Name cannot be blank")
        if (email.isBlank()) return ValidationResult(false,"Email cannot be blank")
        if (password.isBlank()) return ValidationResult(false,"Password cannot be blank")
        return ValidationResult(true)
    }

}