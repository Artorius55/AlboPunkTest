package com.arthur.albo.punktest.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.arthur.albo.punktest.data.model.Beer

@Dao
interface BeerDao {
    @Query("SELECT * FROM beers")
    fun getAllBeers() : LiveData<List<Beer>>

    @Query("SELECT * FROM beers WHERE id = :id")
    fun getBeer(id: Int): LiveData<Beer>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(beers: List<Beer>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(beer: Beer)
}