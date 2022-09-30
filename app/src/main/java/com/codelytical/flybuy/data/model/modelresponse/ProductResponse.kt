package com.codelytical.flybuy.data.model.modelresponse


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "wishlist")
data class ProductResponse(
    @SerializedName("category")
    val category: String,
    @SerializedName("description")
    val description: String,
    @PrimaryKey(autoGenerate = false)
    @SerializedName("_id")
    val id: String,
    @SerializedName("quantity")
    val quantity: Int,
    @SerializedName("image")
    val image: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("ratings")
    val rating: Rating,
    @SerializedName("name")
    val name: String,
    @SerializedName("__v")
    val v: Int,
) : Serializable

data class Rating(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("rate")
    val rate: Double? = 0.0
)