package com.example.noteappjetpackcompose.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.noteappjetpackcompose.presentation.screens.MainScreen
import com.example.noteappjetpackcompose.presentation.screens.NoteScreen
import com.example.noteappjetpackcompose.presentation.viewModel.MainViewModel
import com.example.noteappjetpackcompose.presentation.viewModel.NoteViewModel
import org.koin.androidx.compose.koinViewModel

sealed class AppScreens(val route: String) {
    data object MainScreen : AppScreens("mainScreen")
    data object NoteScreen : AppScreens("noteScreen")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.MainScreen.route) {
        composable(AppScreens.MainScreen.route) {
            val viewModel: MainViewModel = koinViewModel()
            val notes by viewModel.note.collectAsState()
            val state by viewModel.state.collectAsState()
            MainScreen(
                viewModel = viewModel,
                state = state,
                notes = notes,
                onNewNoteScreenClickListener = {
                    navController.navigate(AppScreens.NoteScreen.route)
                },
                onNoteScreenClickListener = { index ->
                    navController.navigate("${AppScreens.NoteScreen.route}?noteId=${notes[index].id}")
                })
        }
        composable(
            "${AppScreens.NoteScreen.route}?noteId={noteId}",
            arguments = listOf(navArgument("noteId") {
                type = NavType.StringType
                nullable = true
            })
        ) { stackEntry ->
            val viewModel: NoteViewModel = koinViewModel()
            val state by viewModel.state.collectAsState()
            val noteId = stackEntry.arguments?.getString("noteId")
            NoteScreen(viewModel = viewModel, transferNoteId = noteId, state = state)
        }
    }
}