package com.sample.criminalintent.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.criminalintent.usecase.GetIntentsFromDbUseCase
import com.sample.criminalintent.model.Intent
import kotlinx.coroutines.launch

class IntentViewModel (
    private val getIntentsFromDbUseCase: GetIntentsFromDbUseCase
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
                    date = it.date
                )
            }.toMutableList()
        }
    }
}