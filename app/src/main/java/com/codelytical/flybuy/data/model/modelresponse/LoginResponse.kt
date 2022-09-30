package com.codelytical.flybuy.data.model.modelresponse

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class LoginResponse(
    val token: String,
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("_id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("__v")
    val v: Int
): Serializable
