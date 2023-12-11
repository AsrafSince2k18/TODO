package com.example.todopr.ui.presentance.component.navGrpah

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.todopr.ui.presentance.component.addNote.AddNote
import com.example.todopr.ui.presentance.component.mainScreen.ShowScreen

@Composable
fun NavGraph(navHostController : NavHostController) {

    NavHost(navController = navHostController,
        startDestination = "home_screen"){
        composable(route = "home_screen"){
            ShowScreen(onAddNote =
                        {navHostController.navigate("detail_screen?noteId=noteId")},
                onItemClick = {
                    navHostController.navigate("detail_screen?noteId=$it")
                })
        }
        composable(route="detail_screen?noteId={noteId}",
            arguments = listOf(navArgument(name = "noteId"){
                type = NavType.IntType
                defaultValue = -1
            })
        ){
            AddNote(onBackClick = { navHostController.navigateUp() },
                onSuccess = {navHostController.navigateUp()})

        }
    }

}