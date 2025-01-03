package com.sample.criminalintent

class RemoveIntentsFromDbUseCase (private val repository: IntentRepository){
    suspend operator fun invoke(movieEntity: List<IntentEntity>) {
        repository.removeIntentsFromDb(movieEntity)
    }
}