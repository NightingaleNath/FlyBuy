package com.codelytical.flybuy.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.codelytical.flybuy.data.model.modelresponse.ProductResponse

@Database(entities = [ProductResponse::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class FlyBuyDatabase : RoomDatabase(){

    abstract fun flyBuyDAO() : FlyBuyDAO

}