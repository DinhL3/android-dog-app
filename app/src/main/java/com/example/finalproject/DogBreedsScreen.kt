package com.example.finalproject

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    var text by rememberSaveable { mutableStateOf("") }
    var active by rememberSaveable { mutableStateOf(false) }

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
            when {
                viewState.loading -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        CircularProgressIndicator(
                            color = OliveGreen
                        )
                    }
                }

                viewState.error != null -> {
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Text("Error: ${viewState.error}")
                    }
                }

                else -> {
                    val filteredBreeds = viewState.breedsList.filter { breed ->
                        breed.name.contains(text, ignoreCase = true)
                    }

                    Column(modifier = Modifier.padding(innerPadding)){
                        SearchBar(
                            query = text,
                            onQueryChange = { text = it },
                            onSearch = { active = false },
                            active = false,
                            onActiveChange = { },
                            placeholder = { Text("Search...") },
                            trailingIcon = { Icon(Icons.Default.Search, contentDescription = null) },
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 16.dp, vertical = 8.dp)
                        ){}

                        LazyColumn{
                            items(filteredBreeds) { breed ->
                                ListItem(
                                    modifier = Modifier.clickable { navController.navigate("breeds/${breed.id}") },
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.Transparent,

                                    ),
                                    headlineContent = {
                                        Text(breed.name)
                                    },
                                    supportingContent = {
                                        Text(breed.breed_group ?: "Unknown", color = Color.Gray)
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
}

