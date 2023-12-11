package com.example.todopr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.todopr.ui.presentance.component.mainScreen.ShowScreen
import com.example.todopr.ui.presentance.component.navGrpah.NavGraph
import com.example.todopr.ui.theme.TODOPRTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TODOPRTheme {
                val navHostController = rememberNavController()
                NavGraph(navHostController = navHostController)
            }
        }
    }
}

