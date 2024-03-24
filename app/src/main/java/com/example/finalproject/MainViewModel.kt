package com.example.finalproject

import android.graphics.Bitmap
import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _dogBreedsState = mutableStateOf(DogBreedState())
    val dogBreedsState: State<DogBreedState> = _dogBreedsState

    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun onTakePhoto(bitmap: Bitmap){
        Log.d("MainViewModel", "Code reached here")
        _bitmaps.value += bitmap
        Log.d("MainViewModel", "Saved picture success: ${_bitmaps.value}")
    }

    fun fetchBreeds() {
        viewModelScope.launch {
            try {
                val response = dogBreedsService.getBreeds()
                _dogBreedsState.value = _dogBreedsState.value.copy(
                    loading = false,
                    breedsList = response,
                    error = null
                )
            } catch (e: Exception) {
                _dogBreedsState.value = _dogBreedsState.value.copy(
                    loading = false,
                    error = "Error fetching dog breeds ${e.message}"
                )
                Log.e("MainViewModel", "${e.message}")
            }
        }
    }

    fun fetchBreedById(id: String) {
        viewModelScope.launch {
            try {
                val response = dogBreedsService.getBreedById(id)
                _dogBreedsState.value = _dogBreedsState.value.copy(
                    loading = false,
                    breed = response,
                    error = null
                )
//                Log.d("MainViewModel", "Successfully fetched breed with id: $id")
            } catch (e: Exception) {
                _dogBreedsState.value = _dogBreedsState.value.copy(
                    loading = false,
                    error = "Error fetching dog breed ${e.message}"
                )
//                Log.e("MainViewModel", "${e.message}")
            }
        }
    }

    fun fetchImageById(id: String) {
        viewModelScope.launch {
            try {
                val response = dogBreedsService.getImageById(id)
                _dogBreedsState.value = _dogBreedsState.value.copy(
                    loading = false,
                    image = response,
                    error = null,
                )
                Log.d("MainViewModel", "Successfully fetched image with id: $id")
            } catch (e: Exception) {
                _dogBreedsState.value = _dogBreedsState.value.copy(
                    loading = false,
                    error = "Error fetching image ${e.message}"
                )
                Log.e("MainViewModel", "${e.message}")
            }
        }
    }

    data class DogBreedState(
        val loading: Boolean = true,
        val breedsList: List<Breed> = emptyList(),
        val breed: Breed? = null,
        val error: String? = null,
        val image: Image? = null
    )
}