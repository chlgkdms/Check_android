package com.example.check

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.check.navigation.CheckApp
import com.example.check.ui.theme.CheckTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CheckTheme {
                CheckApp()
            }
        }
    }
}