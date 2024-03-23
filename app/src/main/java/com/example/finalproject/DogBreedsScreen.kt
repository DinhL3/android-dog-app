package com.example.finalproject

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.finalproject.ui.theme.CreamWhite
import com.example.finalproject.ui.theme.OliveGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedsScreen(navController: NavHostController) {
    val dogBreedsViewModel: MainViewModel = viewModel()
    val viewState by dogBreedsViewModel.dogBreedsState

    LaunchedEffect(key1 = Unit) {
        dogBreedsViewModel.fetchBreeds()
    }

    Scaffold(
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
                    IconButton(onClick = {
                        navController.navigate("home")
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Back",
                            tint = CreamWhite
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = CreamWhite
        ) {
            Column(modifier = Modifier.padding(innerPadding)) {
                when {
                    viewState.loading -> {
                        Box(Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center) {
                            CircularProgressIndicator()
                        }
                    }

                    viewState.error != null -> {
                        Text("Error: ${viewState.error}")
                    }

                    else -> {
                        viewState.breedsList.forEach { breed ->
                            ListItem(
                                modifier = Modifier.clickable { navController.navigate("breeds/${breed.id}") },
                                colors = ListItemDefaults.colors(
                                    containerColor = Color.Transparent,
                                    ),
                                headlineContent = {
                                    Text(breed.attributes.name)
                                },
                                supportingContent = {
                                    //calc average weight, if < 24 lbs, small, if < 59 lbs, medium, if <99 lbs Large, else Giant
                                    val avgWeight =
                                        (breed.attributes.male_weight.min + breed.attributes.female_weight.max + breed.attributes.female_weight.min + breed.attributes.male_weight.max) / 4
                                    if (avgWeight < 24) {
                                        Text("Small")
                                    } else if (avgWeight < 59) {
                                        Text("Medium")
                                    } else if (avgWeight < 99) {
                                        Text("Large")
                                    } else {
                                        Text("Giant")
                                    }
                                },
                            )
                            HorizontalDivider()
                        }
                    }
                }
            }
        }
    }
}

