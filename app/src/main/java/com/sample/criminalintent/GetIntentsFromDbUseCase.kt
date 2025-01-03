package com.sample.criminalintent

class GetIntentsFromDbUseCase(private val repository: IntentRepository) {
    suspend operator fun invoke(): List<IntentEntity> {
        return repository.getIntentsFromDb()
    }
}