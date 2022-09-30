package com.codelytical.flybuy.data.model.modelrequest


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserRequest(
    @SerializedName("address")
    val address: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("password")
    val password: String,
) : Serializable