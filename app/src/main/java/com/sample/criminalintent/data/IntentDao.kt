package com.sample.criminalintent.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface IntentDao {
    @Query("SELECT * FROM intents")
    suspend fun getAllIntents(): List<IntentEntity>

    @Query("SELECT * FROM intents WHERE id = :id")
    suspend fun getIntentById(id: Int): IntentEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIntents(intents: List<IntentEntity>)

    @Delete
    suspend fun deleteIntents(intents: List<IntentEntity>)

    @Update
    suspend fun updateIntents(intents: List<IntentEntity>)
}