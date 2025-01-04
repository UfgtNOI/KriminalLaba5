package com.sample.criminalintent.usecase

import com.sample.criminalintent.IntentRepository
import com.sample.criminalintent.data.IntentEntity

class UpdateIntentsInDbUseCase(private val repository: IntentRepository) {
    suspend operator fun invoke(intents: List<IntentEntity>) {
        repository.updateIntentsInDb(intents)
    }
}