package com.sample.criminalintent

class SaveIntentsToDbUseCase(private val repository: IntentRepository) {
    suspend operator fun invoke(movieEntity: List<IntentEntity>) {
        repository.saveIntentsToDb(movieEntity)
    }
}