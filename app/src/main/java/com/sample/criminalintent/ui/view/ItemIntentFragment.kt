package com.sample.criminalintent.ui.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.sample.criminalintent.usecase.SaveIntentsToDbUseCase
import com.sample.criminalintent.databinding.FragmentIntentBinding
import com.sample.criminalintent.ui.viewmodel.ItemIntentViewModel
import com.sample.criminalintent.usecase.GetIntentByIdFromDbUseCase
import com.sample.criminalintent.usecase.RemoveIntentsFromDbUseCase
import com.sample.criminalintent.usecase.UpdateIntentsInDbUseCase
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

const val ARG_INTENT_ID = "intentId"

class ItemIntentFragment : Fragment(), DIAware {
    override val di by closestDI()
    private var intentId: Int? = null
    private lateinit var binding: FragmentIntentBinding

    private val saveIntentsToDbUseCase : SaveIntentsToDbUseCase by instance()
    private val updateIntentsInDbUseCase: UpdateIntentsInDbUseCase by instance()
    private val getIntentByIdFromDbUseCase: GetIntentByIdFromDbUseCase by instance()
    private val removeIntentsFromDbUseCase: RemoveIntentsFromDbUseCase by instance()
    private lateinit var viewModel: ItemIntentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntentBinding.inflate(inflater, container, false)
        val root = binding.root

        arguments?.let {
            intentId = it.getInt(ARG_INTENT_ID)
        }
        viewModel = ItemIntentViewModel(saveIntentsToDbUseCase, updateIntentsInDbUseCase, getIntentByIdFromDbUseCase, removeIntentsFromDbUseCase,  intentId)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        binding.RemoveButton.isVisible = intentId != null

        return root
    }

    companion object {
        @JvmStatic
        fun newInstance(intentId: Int) =
            ItemIntentFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_INTENT_ID, intentId)
                }
            }
    }
}