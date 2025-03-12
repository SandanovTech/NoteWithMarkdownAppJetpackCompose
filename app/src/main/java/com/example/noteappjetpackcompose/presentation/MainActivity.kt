package com.example.noteappjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.noteappjetpackcompose.presentation.navigation.AppNavHost
import com.example.noteappjetpackcompose.presentation.theme.NoteAppJetpackComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NoteAppJetpackComposeTheme {
                AppNavHost()
            }
        }
    }
}