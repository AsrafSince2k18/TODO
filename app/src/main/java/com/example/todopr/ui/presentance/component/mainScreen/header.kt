package com.example.todopr.ui.presentance.component.mainScreen

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopHeader() {

    TopAppBar(title = {
        Text(text = "TODO",
            style = MaterialTheme.typography.bodyMedium,
            fontWeight = FontWeight.Bold) },

        )

}