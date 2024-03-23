package com.example.finalproject

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController

@Composable
fun DogBreedDetailsScreen(navController: NavHostController){
    val dogBreedsViewModel: MainViewModel = viewModel()
    val viewState by dogBreedsViewModel.dogBreedsState
}