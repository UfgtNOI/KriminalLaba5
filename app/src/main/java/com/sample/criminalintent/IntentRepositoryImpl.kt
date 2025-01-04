package com.sample.criminalintent

import com.sample.criminalintent.data.IntentEntity

class IntentRepositoryImpl(
    private val localDataSource: IntentLocalDataSource
): IntentRepository {
    override suspend fun getIntentsFromDb(): List<IntentEntity> {
        return localDataSource.getIntents()
    }

    override suspend fun getIntentByIdFromDb(id: Int): IntentEntity {
        return localDataSource.getIntentById(id)
    }

    override suspend fun saveIntentsToDb(intents: List<IntentEntity>) {
        localDataSource.saveIntents(intents)
    }

    override suspend fun removeIntentsFromDb(intents: List<IntentEntity>) {
        localDataSource.removeIntents(intents)
    }

    override suspend fun updateIntentsInDb(intents: List<IntentEntity>) {
        localDataSource.updateIntents(intents)
    }
}