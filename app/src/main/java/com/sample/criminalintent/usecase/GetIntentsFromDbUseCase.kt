package com.sample.criminalintent.usecase

import com.sample.criminalintent.IntentRepository
import com.sample.criminalintent.data.IntentEntity

class GetIntentsFromDbUseCase(private val repository: IntentRepository) {
    suspend operator fun invoke(): List<IntentEntity> {
        return repository.getIntentsFromDb()
    }
}