package formulario.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDateTime

@Composable
fun SolicitudScreen(
    navController: NavController
) {
    var solicitud by remember { mutableStateOf(Solicitud()) }
    var isSending by remember { mutableStateOf(false) }
    val composableScope = rememberCoroutineScope()

    val p = solicitud.prioridad.toIntOrNull() ?: 0
    val isValidate = solicitud.titulo.length in 5..60 &&
            solicitud.descripcion.length in 20..500 &&
            p in 1..5 &&
            solicitud.email.contains("@gmail.com") &&
            solicitud.email.length > 10


    Column(
        modifier = Modifier.padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Formulario",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary,
        )

        Spacer(Modifier.height(30.dp))
        Text(text = "Registro", style = MaterialTheme.typography.bodyMedium)

        Spacer(Modifier.height(20.dp))

        OutlinedTextField(
            value = solicitud.titulo,
            onValueChange = { solicitud = solicitud.copy(titulo = it) },
            label = { Text("Titulo") },
            isError = (solicitud.titulo.length < 5) || (solicitud.titulo.length > 60),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = solicitud.descripcion,
            onValueChange = { solicitud = solicitud.copy(descripcion = it) },
            label = { Text("Descripcion") },
            isError = (solicitud.descripcion.length < 20) || (solicitud.descripcion.length > 500),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = solicitud.categoria,
            onValueChange = { solicitud = solicitud.copy(categoria = it) },
            label = { Text("Categoria") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = solicitud.prioridad,
            onValueChange = { solicitud = solicitud.copy(prioridad = it) },
            label = { Text("Prioridad") },
            isError = solicitud.prioridad.toIntOrNull()?.let { it !in 1..5 } ?: true,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = solicitud.email,
            onValueChange = { solicitud = solicitud.copy(email = it) },
            label = { Text("Email") },
            isError = !solicitud.email.contains("@gmail.com") && solicitud.email.length > 10,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(Modifier.height(12.dp))

        Button(
            onClick = {
                if (isValidate && !isSending) {
                    isSending = true
                    composableScope.launch {
                        try {
                            val finalSolicitud =
                                solicitud.copy(fecha = LocalDateTime.now().toString())

                            withContext(Dispatchers.IO) {
                                supabase.from("solicitudes").insert(finalSolicitud)
                            }

                        } catch (e: Exception) {
                            e.printStackTrace()
                        } finally {
                            isSending = false
                        }
                    }
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
                navController.navigate(
                    "login"
                )
            } else {
                Text("Guardar")
            }
        }

    }
}




