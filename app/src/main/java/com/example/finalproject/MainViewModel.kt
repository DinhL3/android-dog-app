package com.example.finalproject

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _dogBreedsState = mutableStateOf(DogBreedState())
    val dogBreedsState: State<DogBreedState> = _dogBreedsState

    init {
        fetchBreeds()
    }

    private fun fetchBreeds() {
        viewModelScope.launch {
            try {
                val response = dogBreedsService.getBreeds()
                _dogBreedsState.value = _dogBreedsState.value.copy(
                    loading = false,
                    list = response.data,
                    error = null
                )
            } catch (e: Exception) {
                _dogBreedsState.value = _dogBreedsState.value.copy(
                    loading = false,
                    error = "Error fetching dog breeds ${e.message}"
                )
            }
        }
    }

    data class DogBreedState(
        val loading: Boolean = true,
        val list: List<Breed> = emptyList(),
        val error: String? = null
    )
}