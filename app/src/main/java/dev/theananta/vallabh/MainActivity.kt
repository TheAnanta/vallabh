package dev.theananta.vallabh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import dev.theananta.vallabh.ui.VallabhNavHost
import dev.theananta.vallabh.ui.theme.VallabhTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VallabhTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    VallabhNavHost(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

