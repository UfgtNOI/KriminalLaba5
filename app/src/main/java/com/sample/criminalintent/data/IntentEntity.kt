package com.sample.criminalintent.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "intents")
data class IntentEntity(
    val title: String?,
    val description: String?,
    val date: Long?,
    val isDone: Boolean,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    val photo: ByteArray
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0


    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as IntentEntity

        if (title != other.title) return false
        if (description != other.description) return false
        if (date != other.date) return false
        if (isDone != other.isDone) return false
        if (!photo.contentEquals(other.photo)) return false
        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        var result = title?.hashCode() ?: 0
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (date?.hashCode() ?: 0)
        result = 31 * result + isDone.hashCode()
        result = 31 * result + photo.contentHashCode()
        result = 31 * result + id
        return result
    }
}