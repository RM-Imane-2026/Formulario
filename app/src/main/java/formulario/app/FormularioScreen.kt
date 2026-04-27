package formulario.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import java.time.LocalDateTime

@Composable
fun FormularioScreen(
    navController: NavController
) {
    var titulo by remember { mutableStateOf("") }
    var descripcion by remember { mutableStateOf("") }
    var categoria by remember { mutableStateOf("") }
    var prioridad by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }

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
            value = titulo,
            onValueChange = { titulo = it },
            label = { Text("Titulo") },
            isError = (titulo.length < 5) || (titulo.length > 60)

        )
        Spacer(Modifier.height(8.dp))

        OutlinedTextField(
            value = descripcion,
            onValueChange = { descripcion = it },
            label = { Text("Descripcion") },
            isError = (descripcion.length < 20) || (descripcion.length > 500)
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = categoria,
            onValueChange = { categoria = it },
            label = { Text("Categoria") }
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = prioridad,
            onValueChange = { prioridad = it },
            label = { Text("Prioridad") },
            isError = prioridad.toIntOrNull()?.let { it !in 1..5 } ?: true
        )
        Spacer(Modifier.height(8.dp))
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            isError = !email.contains("@gmail.com")
        )
        Spacer(Modifier.height(12.dp))
        Button(onClick = {
            val p = prioridad.toIntOrNull() ?: 0
            if (titulo.length in 5..60 &&
                descripcion.length in 20..500 &&
                p in 1..5 &&
                email.contains("@gmail.com")
            ) {
                fecha = LocalDateTime.now().toString()  //guardar la fecha ya que en "mis solicitudes" la voy a ordenar en descendiente.
                navController.popBackStack()
            }
        }) { Text("Guardar") }

    }
}


