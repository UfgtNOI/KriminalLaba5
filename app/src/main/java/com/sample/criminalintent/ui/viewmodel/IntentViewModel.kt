package com.sample.criminalintent.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.criminalintent.usecase.GetIntentsFromDbUseCase
import com.sample.criminalintent.model.Intent
import com.sample.criminalintent.usecase.RemoveIntentsFromDbUseCase
import com.sample.criminalintent.usecase.SaveIntentsToDbUseCase
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