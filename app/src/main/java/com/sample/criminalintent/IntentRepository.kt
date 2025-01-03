package com.sample.criminalintent

interface IntentRepository {
    suspend fun getIntentsFromDb(): List<IntentEntity>
    suspend fun saveIntentsToDb(intents: List<IntentEntity>)
    suspend fun removeIntentsFromDb(intents: List<IntentEntity>)
}