package com.arthur.albo.punktest.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.arthur.albo.punktest.data.model.Beer

@Database(entities = [Beer::class], version = 3, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun beerDao(): BeerDao

    companion object {
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it } }

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java, "beers")
                .fallbackToDestructiveMigration()
                .build()
    }

}