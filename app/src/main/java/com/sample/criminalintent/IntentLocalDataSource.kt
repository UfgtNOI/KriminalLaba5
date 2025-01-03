package com.sample.criminalintent

class IntentLocalDataSource(private val intentDao: IntentDao) {
    suspend fun getIntents(): List<IntentEntity> {
        return intentDao.getAllIntents()
    }

    suspend fun saveIntents(intents: List<IntentEntity>) {
        intentDao.insertIntents(intents)
    }

    suspend fun removeIntents(intents: List<IntentEntity>) {
        intentDao.deleteIntents(intents)
    }
}