package com.codelytical.flybuy.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.codelytical.flybuy.data.model.modelresponse.Rating
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import javax.inject.Inject

@ProvidedTypeConverter
class Converters @Inject constructor(
    private val gson: Gson
) {

    @TypeConverter
    fun fromRating(rating : Rating): String {
        return gson.toJson(rating)
    }

    @TypeConverter
    fun toRating(json : String) : Rating {
        val type = object : TypeToken<Rating>() {}.type
        return gson.fromJson(json,type)
    }

}