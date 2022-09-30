package com.codelytical.flybuy.presentation.di

import android.app.Application
import androidx.room.Room
import com.codelytical.flybuy.data.db.Converters
import com.codelytical.flybuy.data.db.FlyBuyDAO
import com.codelytical.flybuy.data.db.FlyBuyDatabase
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun providesShopDatabase(app : Application,gson : Gson) : FlyBuyDatabase{
        return Room.databaseBuilder(app,FlyBuyDatabase::class.java,"shop_database")
            .fallbackToDestructiveMigration()
            .addTypeConverter(Converters(gson))
            .build()
    }

    @Singleton
    @Provides
    fun providesFlyBuyDAO(database: FlyBuyDatabase) : FlyBuyDAO {
        return database.flyBuyDAO()
    }

}