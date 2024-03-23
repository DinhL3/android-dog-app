package com.example.finalproject

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.example.finalproject.ui.theme.CreamWhite
import com.example.finalproject.ui.theme.OliveGreen
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.finalproject.ui.theme.DarkOrange
import com.example.finalproject.ui.theme.LightOrange

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedDetailsScreen(navController: NavHostController, breedId: String?) {
    val dogBreedsViewModel: MainViewModel = viewModel()
    val viewState by dogBreedsViewModel.dogBreedsState

    val breed = viewState.breed
    val image = viewState.image
    val imageUrl = image?.url
    val imageWidth = image?.width?.toFloat()
    val imageHeight = image?.height?.toFloat()
    val aspectRatio = if (imageWidth != null && imageHeight != null && imageHeight != 0f) {
        imageWidth / imageHeight
    } else {
        1f
    }

    LaunchedEffect(key1 = breedId, key2 = breed?.reference_image_id) {
        if (breedId != null) {
            dogBreedsViewModel.fetchBreedById(breedId)
        }
        if (breed?.reference_image_id != null) {
            breed.reference_image_id.let { dogBreedsViewModel.fetchImageById(it) }
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = DarkOrange,
                    titleContentColor = CreamWhite
                ),
                title = {
                    Text(breed?.name ?: "Dog Breed")
                },
                navigationIcon = {
                    IconButton(onClick = {
                        navController.popBackStack()
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
                        Text("Error: ${viewState.error}")
                    }

                    else -> {
                        // use lazy column and ListItem with headlineContent and supportingContent
                        LazyColumn {
                            item{                               

                                Image(
                                    painter = rememberAsyncImagePainter(imageUrl),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .aspectRatio(aspectRatio)
                                )
                            }
                            item {
                                ListItem(
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.Transparent
                                    ),
                                    headlineContent = {
                                        Text(
                                            text = "Group",
                                            color = DarkOrange,
                                            fontWeight = FontWeight.Bold
                                        )
                                    },
                                    supportingContent = {
                                        Text(
                                            text = breed?.breed_group ?: "Unknown",
                                        )
                                    }
                                )
                            }
                            item{
                                ListItem(
                                    colors = ListItemDefaults.colors(
                                        containerColor = Color.Transparent
                                    ),
                                    headlineContent = {
                                        Text(
                                            text = "Bred for",
                                            color = DarkOrange,
                                            fontWeight = FontWeight.Bold
                                        )
                                    },
                                    supportingContent = {
                                        Text(
                                            text = breed?.bred_for ?: "Unknown",
                                        )
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}