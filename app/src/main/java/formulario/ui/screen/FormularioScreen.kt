package formulario.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import formulario.domain.model.Solicitud
import formulario.ui.viewmodel.FormularioViewModel
import java.time.LocalDateTime

@Composable
fun FormularioScreen(
    navController: NavController,
    viewModel: FormularioViewModel = hiltViewModel()
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var isSending by remember { mutableStateOf(false) }

    val emailValido = !email.startsWith("@") && email.contains("@") &&
                     (email.endsWith(".com") || email.endsWith(".es"))
    
    val prioridadValida = prioridad.toLongOrNull() ?: 0L

    val isValidate = titulo.length in 5..60 &&
            descripcion.length in 20..500 &&
            prioridadValida in 1..5 &&
            emailValido

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(24.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Crear Solicitud",
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(24.dp))

            OutlinedTextField(
                value = titulo,
                onValueChange = { titulo = it },
                label = { Text("Título (5-60 caracteres)") },
                isError = titulo.isNotEmpty() && titulo.length !in 5..60,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = descripcion,
                onValueChange = { descripcion = it },
                label = { Text("Descripción (20-500 caracteres)") },
                isError = descripcion.isNotEmpty() && descripcion.length !in 20..500,
                modifier = Modifier.fillMaxWidth(),
                minLines = 3
            )

            OutlinedTextField(
                value = categoria,
                onValueChange = { categoria = it },
                label = { Text("Categoría") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = prioridad,
                onValueChange = { prioridad = it },
                label = { Text("Prioridad (1-5)") },
                isError = prioridad.isNotEmpty() && prioridadValida !in 1..5,
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = email,
                onValueChange = { email = it },
                label = { Text("Email (debe tener '@' y terminar en .com o .es)") },
                isError = email.isNotEmpty() && !emailValido,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(32.dp))

            Button(
                onClick = {
                    if (isValidate && !isSending) {
                        val item = Solicitud(
                            id = 0L,
                            titulo = titulo,
                            descripcion = descripcion,
                            categoria = categoria,
                            prioridad = prioridadValida,
                            email = email,
                            fecha = LocalDateTime.now().toString()
                        )
                        viewModel.enviarSolicitud(item)
                        navController.popBackStack()
                        isSending = true
                    }
                },
                enabled = isValidate && !isSending,
                modifier = Modifier.fillMaxWidth()
            ) {
                if (isSending) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp,
                        color = MaterialTheme.colorScheme.onPrimary
                    )
                    Spacer(Modifier.width(8.dp))
                    Text("Enviando...")
                } else {
                    Text("Guardar")
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Cancelar")
            }
        }
    }
}
