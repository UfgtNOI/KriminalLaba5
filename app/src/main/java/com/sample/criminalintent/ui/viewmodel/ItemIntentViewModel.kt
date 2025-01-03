package com.sample.criminalintent.ui.viewmodel

import android.util.Log
import android.view.View
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sample.criminalintent.usecase.SaveIntentsToDbUseCase
import com.sample.criminalintent.data.IntentEntity
import kotlinx.coroutines.launch
import java.util.GregorianCalendar

class ItemIntentViewModel(
    private val saveIntentsToDbUseCase: SaveIntentsToDbUseCase
) : ViewModel(){

    var title: String = ""
    var description: String = ""
    var isSolved: ObservableBoolean = ObservableBoolean(false)
    var year: Int? = null
    var month: Int? = null
    var day: Int? = null

    fun saveIntent(view: View){

        Log.w("myApp", if(isSolved.get())"no" else "yes");

        val date = GregorianCalendar(year!!, month!!, day!!)
        val title = this.title
        val description = this.description
        val isSolved = this.isSolved.get()
        viewModelScope.launch {
            saveIntentsToDbUseCase.invoke(
                listOf(
                    IntentEntity(
                        title = title,
                        description = description,
                        isDone = isSolved,
                        date = date.timeInMillis,
                        photo = ByteArray(0)
                    )
                )
            )
        }
    }

}