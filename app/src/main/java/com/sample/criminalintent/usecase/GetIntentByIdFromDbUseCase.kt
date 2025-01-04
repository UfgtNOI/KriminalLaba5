package com.sample.criminalintent.usecase

import com.sample.criminalintent.IntentRepository
import com.sample.criminalintent.data.IntentEntity

class GetIntentByIdFromDbUseCase(private val repository: IntentRepository) {
    suspend operator fun invoke(id: Int): IntentEntity {
        return repository.getIntentByIdFromDb(id)
    }
}