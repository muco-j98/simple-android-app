package com.muco.myapplication3

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BreweryViewModel @Inject constructor(
    private val breweryRepository: BreweryRepository
): ViewModel() {

    private val _breweriesStateFlow = MutableStateFlow<BreweriesUiState>(BreweriesUiState.Loading)
    val breweriesStateFlow = _breweriesStateFlow.asStateFlow()

    init {
        viewModelScope.launch {
            val result = breweryRepository.getBreweries()
            if (result.isSuccessful && result.body() != null) {
                _breweriesStateFlow.value = BreweriesUiState.Success(result.body()!!)
            } else {
                _breweriesStateFlow.value = BreweriesUiState.Error
            }
        }
    }
}

sealed class BreweriesUiState {
    data object Loading: BreweriesUiState()
    data class Success(val breweryModels: List<BreweryModel>): BreweriesUiState()
    data object Error: BreweriesUiState()
}