package com.sample.criminalintent.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intents")
data class IntentEntity(
    var title: String?,
    var description: String?,
    var date: Long?,
    var isDone: Boolean
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}