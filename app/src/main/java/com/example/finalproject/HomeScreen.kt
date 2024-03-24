package com.example.finalproject

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.Pets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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

@Composable
fun HomeScreen(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(0.dp)
            .background(CreamWhite),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Dinh's Dog App \uD83D\uDC36",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = OliveGreen,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Button(
            onClick = { navController.navigate("breeds") },
            modifier = Modifier.padding(bottom = 8.dp),
            colors = ButtonDefaults.buttonColors(DarkOrange)
        ) {
            Icon(
                Icons.Filled.Pets,
                contentDescription = "Pets",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "See dog breeds")
        }
        Button(
            onClick = { navController.navigate("camera")},
            modifier = Modifier.padding(bottom = 0.dp),
            colors = ButtonDefaults.buttonColors(DarkOrange)
        ) {
            Icon(
                Icons.Filled.AddAPhoto,
                contentDescription = "Add picture",
                modifier = Modifier.size(ButtonDefaults.IconSize),
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text(text = "Take a picture of your dog")
        }
    }
}