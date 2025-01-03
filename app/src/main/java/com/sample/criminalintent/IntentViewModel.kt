package com.sample.criminalintent

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import kotlinx.coroutines.launch

class IntentViewModel (
    private val getIntentsFromDbUseCase: GetIntentsFromDbUseCase,
    private val saveIntentsToDbUseCase: SaveIntentsToDbUseCase,
    private val removeIntentsFromDbUseCase: RemoveIntentsFromDbUseCase,
): ViewModel() {

    private val _intents = MutableLiveData<MutableList<Intent>>()
    val intents: LiveData<MutableList<Intent>> get() = _intents

    fun loadIntents() {
        viewModelScope.launch {
            _intents.value = getIntentsFromDbUseCase.invoke().map{
                Intent(
                    id = it.id,
                    title = it.title,
                    description = it.description,
                    isDone = it.isDone,
                    photo = it.photo,
                    date = it.date
                )
            }.toMutableList()
        }
    }
}