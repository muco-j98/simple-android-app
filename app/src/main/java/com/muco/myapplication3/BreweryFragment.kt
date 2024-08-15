package com.muco.myapplication3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.muco.myapplication3.databinding.FragmentBreweryBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BreweryFragment : Fragment() {

    private val vm: BreweryViewModel by viewModels()

    private var _binding: FragmentBreweryBinding? = null
    private val binding = _binding!!

    private lateinit var breweryAdapter: BreweryAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentBreweryBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        breweryAdapter = BreweryAdapter()

        binding.breweryRecyclerView.apply {
            adapter = breweryAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vm.breweriesStateFlow.collect { uiState ->
                    when (uiState) {
                        is BreweriesUiState.Success -> {
                            breweryAdapter.submitList(uiState.breweryModels)
                            binding.loader.isVisible = false
                        }

                        is BreweriesUiState.Loading -> {
                            binding.breweryRecyclerView.isVisible = false
                            binding.loader.isVisible = true
                        }

                        is BreweriesUiState.Error -> {
                            Toast.makeText(context, "opps an error ocurred", Toast.LENGTH_SHORT)
                                .show()
                            binding.loader.isVisible = false
                        }
                    }
                }
            }
        }
    }
}