package formulario.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import formulario.ui.navigation.Screen

@Composable
fun InicioScreen(
    navigator: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Formulario",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold
        )

        Spacer(Modifier.height(48.dp))

        Button(
            onClick = { navigator.navigate(Screen.Formulario.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Crear nueva solicitud")
        }

        Spacer(Modifier.height(16.dp))

        Button(
            onClick = { navigator.navigate(Screen.Listado.route) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Ver mis solicitudes")
        }
    }
}
