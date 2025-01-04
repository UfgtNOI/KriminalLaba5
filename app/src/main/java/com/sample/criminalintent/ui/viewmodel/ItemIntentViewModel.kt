package com.sample.criminalintent.ui.viewmodel

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import com.sample.criminalintent.usecase.SaveIntentsToDbUseCase
import com.sample.criminalintent.data.IntentEntity
import com.sample.criminalintent.usecase.GetIntentByIdFromDbUseCase
import com.sample.criminalintent.usecase.RemoveIntentsFromDbUseCase
import com.sample.criminalintent.usecase.UpdateIntentsInDbUseCase
import kotlinx.coroutines.launch
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

class ItemIntentViewModel(
    private val saveIntentsToDbUseCase: SaveIntentsToDbUseCase,
    private val updateIntentsInDbUseCase: UpdateIntentsInDbUseCase,
    private val getIntentByIdFromDbUseCase: GetIntentByIdFromDbUseCase,
    private val removeIntentsFromDbUseCase: RemoveIntentsFromDbUseCase,
    private val intentId: Int? = null
) : ViewModel() {
    var title = MutableLiveData("")
    var description = MutableLiveData("")
    var isSolved:MutableLiveData<Boolean> = MutableLiveData(false)
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
                isSolved.value = intentEntity!!.isDone
                val date = GregorianCalendar()
                date.timeInMillis = intentEntity!!.date!!
                year.value = date.get(Calendar.YEAR)
                month.value = date.get(Calendar.MONTH)
                day.value = date.get(Calendar.DAY_OF_MONTH)

            }
        }else{
            val date = GregorianCalendar()
            date.time = Date()
            year.value = date.get(Calendar.YEAR)
            month.value = date.get(Calendar.MONTH)
            day.value = date.get(Calendar.DAY_OF_MONTH)
        }
    }



    fun saveIntent(view: View){
        val date = GregorianCalendar(year.value!!, month.value!!, day.value!!)
        val title = this.title
        val description = this.description
        val isSolved = this.isSolved
        val intentEntity = this.intentEntity
        viewModelScope.launch {
            if(intentEntity == null){
                saveIntentsToDbUseCase.invoke(
                    listOf(
                        IntentEntity(
                            title = title.value,
                            description = description.value,
                            isDone = isSolved.value!!,
                            date = date.timeInMillis
                        )
                    )
                )
            }else{
                intentEntity.title = title.value
                intentEntity.description = description.value
                intentEntity.isDone = isSolved.value!!
                intentEntity.date = date.timeInMillis
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

    fun removeIntent(view: View){
        val dialogBuilder = AlertDialog.Builder(view.context)
        dialogBuilder.setMessage("Are you sure to remove intent?")
        dialogBuilder.setTitle("Attention")
        dialogBuilder.setPositiveButton("Remove") { _, _ -> removeInternal(view) }
        dialogBuilder.setNegativeButton("Cancel") { _, _ -> }

        dialogBuilder.create().show()
    }

    private fun removeInternal(view: View){
        viewModelScope.launch {
            removeIntentsFromDbUseCase.invoke(listOf(intentEntity!!))
            Toast.makeText(view.context, "Removed", Toast.LENGTH_SHORT).show()
            view.findNavController().navigateUp()
        }
    }
}