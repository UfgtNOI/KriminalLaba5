package com.sample.criminalintent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.criminalintent.databinding.FragmentMainBinding
import org.kodein.di.DIAware
import org.kodein.di.android.x.closestDI
import org.kodein.di.instance

class MainFragment : Fragment(), DIAware, IntentListener {

    override val di by closestDI()

    private lateinit var binding: FragmentMainBinding
    private lateinit var intentAdapter: IntentAdapter
    private val viewModel: IntentViewModel by instance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.viewModel = viewModel;
        intentAdapter = IntentAdapter(emptyList(), this)
        binding.IntentRecycleView.adapter = intentAdapter;
        binding.IntentRecycleView.layoutManager = LinearLayoutManager(context)

        viewModel.intents.observe(viewLifecycleOwner) { intents ->
            intentAdapter.updateIntents(intents)
        }

        viewModel.loadIntents()

        binding.addButton.setOnClickListener {
            addReport()
        }

        return root
    }

    private fun addReport(){
        findNavController().navigate(R.id.intentFragment)
    }
    override fun onIntentClicked(intent: Intent) {
        Toast.makeText(context, "Super", Toast.LENGTH_SHORT).show()
    }


}