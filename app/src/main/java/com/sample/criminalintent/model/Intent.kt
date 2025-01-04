package com.sample.criminalintent.model

data class Intent(
    val id : Int,
    val title : String?,
    val description : String?,
    val date : Long?,
    val isDone : Boolean
)