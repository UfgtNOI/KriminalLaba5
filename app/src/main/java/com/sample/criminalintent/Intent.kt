package com.sample.criminalintent

import java.sql.Timestamp
import java.util.Date

data class Intent(
    val id : Int,
    val title : String?,
    val description : String?,
    val date : Long?,
    val isDone : Boolean,
    val photo : ByteArray
)