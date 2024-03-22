package com.example.finalproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalproject.ui.theme.CreamWhite
import com.example.finalproject.ui.theme.DarkOrange
import com.example.finalproject.ui.theme.OliveGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedsScreen(navController: NavHostController) {
    val dogBreedsViewModel: MainViewModel = viewModel()
    val viewState by dogBreedsViewModel.dogBreedsState

    Scaffold (
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = OliveGreen,
                    titleContentColor = CreamWhite
                ),
                title = {
                    Text("Dog Breeds")
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigate("home")
                    }) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = CreamWhite
                        )
                    }
                }
            )
        },
    ) {
        innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = CreamWhite
        ) {
            when {
                viewState.loading -> {
                    Column (modifier = Modifier.padding(innerPadding)) {
                        Text("Loading...")
                    }
                }
                viewState.error != null -> {
                    Column(modifier = Modifier.padding(innerPadding)){
                        Text("Error: ${viewState.error}")
                    }
                }
                else -> {
                    LazyColumn (modifier = Modifier.padding(innerPadding)){
                        viewState.list.forEach { breed ->
                            item {
                                DogBreedItem(breed)
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Composable
//fun DogBreedsList(breeds: List<Breed>) {
//
//}

@Composable
fun DogBreedItem(breed: Breed) {
        Text(
            text = breed.attributes.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
}