package formulario.app

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import io.github.jan.supabase.postgrest.from

@Composable
fun MisSolicitudesScreen(
    navController: NavController
) {
    val solicitudes = remember { mutableStateListOf<Solicitud>() }

    LaunchedEffect(Unit) {
        try {
            val results = supabase.from("solicitudes").select().decodeList<Solicitud>()
            val listaOrdenada = results.sortedByDescending { it.fecha }
            solicitudes.clear()
            solicitudes.addAll(listaOrdenada)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = 50.dp)
    ) {
        Text(
            text = "Mis Solicitudes",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(solicitudes) { solicitud ->
                Column(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = "Solicitud ${solicitudes.indexOf(solicitud) + 1}:",
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(text = "Título: ${solicitud.titulo}")
                    Text(text = "Descripción: ${solicitud.descripcion}")
                    Text(text = "Categoría: ${solicitud.categoria}")
                    Text(text = "Prioridad: ${solicitud.prioridad}")
                    Text(text = "Email: ${solicitud.email}")
                    Text(text = "Fecha: ${solicitud.fecha}")
                    HorizontalDivider(modifier = Modifier.padding(top = 8.dp))
                }
            }
        }

        Spacer(Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Volver")
            }
        }
    }
}
