package com.example.noteappjetpackcompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.example.noteappjetpackcompose.presentation.navigation.AppNavHost
import com.example.noteappjetpackcompose.presentation.theme.NoteAppJetpackComposeTheme
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        var keepSplashScreen = true
        super.onCreate(savedInstanceState)
        splashScreen.setKeepOnScreenCondition { keepSplashScreen }
        lifecycleScope.launch {
            delay(1000L)
            keepSplashScreen = false
        }
        enableEdgeToEdge()
        setContent {
            NoteAppJetpackComposeTheme {
                AppNavHost()
            }
        }
    }
}