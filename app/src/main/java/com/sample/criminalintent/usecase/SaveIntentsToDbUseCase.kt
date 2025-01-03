package com.sample.criminalintent.usecase

import com.sample.criminalintent.IntentRepository
import com.sample.criminalintent.data.IntentEntity

class SaveIntentsToDbUseCase(private val repository: IntentRepository) {
    suspend operator fun invoke(movieEntity: List<IntentEntity>) {
        repository.saveIntentsToDb(movieEntity)
    }
}