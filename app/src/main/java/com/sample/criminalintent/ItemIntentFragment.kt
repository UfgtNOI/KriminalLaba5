package com.sample.criminalintent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sample.criminalintent.databinding.FragmentIntentBinding
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

private const val ARG_INTENT_ID = "intentId"

class ItemIntentFragment : Fragment(), DIAware {
    override val di by closestDI()
    private var intentId: Int? = null
    private lateinit var binding: FragmentIntentBinding

    private val saveIntentsToDbUseCase : SaveIntentsToDbUseCase by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            intentId = it.getInt(ARG_INTENT_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentIntentBinding.inflate(inflater, container, false)

        val root = binding.root;

        binding.viewModel = ItemIntentViewModel(saveIntentsToDbUseCase)
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