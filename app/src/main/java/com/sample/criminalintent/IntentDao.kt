package com.sample.criminalintent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface IntentDao {
    @Query("SELECT * FROM intents")
    suspend fun getAllIntents(): List<IntentEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntents(movies: List<IntentEntity>)

    @androidx.room.Delete
    suspend fun deleteIntents(movies: List<IntentEntity>)
}