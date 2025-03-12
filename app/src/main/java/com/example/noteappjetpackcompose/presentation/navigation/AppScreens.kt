package com.example.noteappjetpackcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteappjetpackcompose.presentation.screens.MainScreen
import com.example.noteappjetpackcompose.presentation.screens.NoteScreen

sealed class AppScreens(val route: String) {
    data object MainScreen : AppScreens("mainScreen")
    data object NoteScreen : AppScreens("noteScreen")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(AppScreens.MainScreen.route) {
            MainScreen(navController)
        }
        composable(
            "${AppScreens.NoteScreen.route}?noteId={noteId}",
            arguments = listOf(navArgument("noteId") {
                type = NavType.StringType
                nullable = true
            })
        ) { stackEntry ->
            val noteId = stackEntry.arguments?.getString("noteId")
            NoteScreen(navController, noteId)
        }
    }
}