package com.sample.criminalintent.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [IntentEntity::class], version = 1)
abstract class IntentDatabase : RoomDatabase() {
    abstract fun intentDao(): IntentDao
}