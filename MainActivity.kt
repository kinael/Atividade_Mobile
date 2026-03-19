package com.example.datatimeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import com.example.datatimeapp.ui.screen.DateTimeScreen
import com.example.datatimeapp.ui.theme.DataTimeAppTheme
import com.example.datatimeapp.viewmodel.DateTimeViewModel

class MainActivity : ComponentActivity() {

    private val viewModel: DateTimeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            DataTimeAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    DateTimeScreen(viewModel = viewModel)
                }
            }
        }
    }
}
