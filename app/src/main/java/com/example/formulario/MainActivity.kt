package com.example.formulario

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.formulario.ui.theme.FormularioTheme
import formulario.app.FormularioScreen
import formulario.app.InicioScreen
import formulario.app.ListFormularioScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormularioTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable ("login") {
                        InicioScreen(navController)
                    }
                    composable(route = "formulario") {
                        FormularioScreen(
                            navController = navController
                        )
                    }
                    composable(route = "lista-formulario") {
                        ListFormularioScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
