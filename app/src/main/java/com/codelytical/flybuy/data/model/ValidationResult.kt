package com.codelytical.flybuy.data.model

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)
