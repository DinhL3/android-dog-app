package com.example.finalproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.finalproject.ui.theme.CreamWhite
import com.example.finalproject.ui.theme.DarkOrange
import com.example.finalproject.ui.theme.OliveGreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogBreedsScreen(navController: NavHostController) {
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
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                ,
                verticalArrangement = Arrangement.spacedBy(16.dp),
            ) {
                Text(
                    "dummy text"
                )
            }
        }
    }
}
