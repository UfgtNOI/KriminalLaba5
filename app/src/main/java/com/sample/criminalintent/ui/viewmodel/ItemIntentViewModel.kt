package com.sample.criminalintent.ui.viewmodel

import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.databinding.ObservableBoolean
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.sample.criminalintent.usecase.SaveIntentsToDbUseCase
import com.sample.criminalintent.data.IntentEntity
import com.sample.criminalintent.usecase.GetIntentByIdFromDbUseCase
import com.sample.criminalintent.usecase.GetIntentsFromDbUseCase
import com.sample.criminalintent.usecase.UpdateIntentsInDbUseCase
import kotlinx.coroutines.launch
import java.util.Date
import java.util.GregorianCalendar

class ItemIntentViewModel(
    private val saveIntentsToDbUseCase: SaveIntentsToDbUseCase,
    private val updateIntentsInDbUseCase: UpdateIntentsInDbUseCase,
    private val getIntentByIdFromDbUseCase: GetIntentByIdFromDbUseCase,
    private val intentId: Int? = null
) : ViewModel() {
    var title = MutableLiveData("")
    var description = MutableLiveData("")
    var isSolved: ObservableBoolean = ObservableBoolean(false)
    var year: MutableLiveData<Int?> = MutableLiveData()
    var month: MutableLiveData<Int?> = MutableLiveData()
    var day: MutableLiveData<Int?> = MutableLiveData()
    private var intentEntity: IntentEntity? = null

    init {
        if(intentId!=null){
            viewModelScope.launch {
                intentEntity = getIntentByIdFromDbUseCase.invoke(intentId)

                title.value = intentEntity!!.title!!
                description.value = intentEntity!!.description!!
                isSolved.set(intentEntity!!.isDone)
                val date = Date(intentEntity!!.date!!)
                year.value = date.year
                month.value = date.month
                day.value = date.day

            }
        }
    }

    fun saveIntent(view: View){
        val date = GregorianCalendar(year.value!!, month.value!!, day.value!!)
        val title = this.title
        val description = this.description
        val isSolved = this.isSolved.get()
        val intentEntity = this.intentEntity;
        viewModelScope.launch {
            if(intentEntity == null){
                saveIntentsToDbUseCase.invoke(
                    listOf(
                        IntentEntity(
                            title = title.value,
                            description = description.value,
                            isDone = isSolved,
                            date = date.timeInMillis,
                            photo = ByteArray(0)
                        )
                    )
                )
            }else{
                intentEntity.title = title.value
                intentEntity.description = description.value
                intentEntity.isDone = isSolved
                intentEntity.date = date.timeInMillis
                intentEntity.photo = ByteArray(0)
                updateIntentsInDbUseCase.invoke(
                    listOf(
                        intentEntity
                    )
                )
            }
        }
        Toast.makeText(view.context, "Saved", Toast.LENGTH_SHORT).show()
        view.findNavController().navigateUp()
    }

}