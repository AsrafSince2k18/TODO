package com.example.todopr.ui.presentance.component.mainScreen

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun NoteFab(
    onClick :() -> Unit
) {
    FloatingActionButton(
        onClick = { onClick()},
        elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(
            defaultElevation = 2.dp, pressedElevation = 4.dp, focusedElevation = 2.dp
        ),
        shape = FloatingActionButtonDefaults.shape,
        containerColor = MaterialTheme.colorScheme.primary) {
        Icon(imageVector = Icons.Default.Add,
            contentDescription ="add" )
    }
}