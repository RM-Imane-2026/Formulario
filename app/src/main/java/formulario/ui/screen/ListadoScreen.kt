package formulario.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import formulario.ui.viewmodel.ListadoViewModel
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

@Composable
fun ListadoScreen(
    navController: NavController,
    viewModel: ListadoViewModel = hiltViewModel()
) {
    val solicitudes by viewModel.solicitudes.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadSolicitudes()
    }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            Text(
                text = "Mis solicitudes",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Bold
            )

            Spacer(Modifier.height(16.dp))

            if (solicitudes.isEmpty()) {
                Box(modifier = Modifier.weight(1f), contentAlignment = Alignment.Center) {
                    Text("No hay solicitudes")
                    //CircularProgressIndicator()
                }
            } else {
                LazyColumn(
                    modifier = Modifier.weight(1f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(solicitudes) { solicitud ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
                        ) {
                            Column(modifier = Modifier.padding(12.dp)) {
                                Text(
                                    text = solicitud.titulo,
                                    style = MaterialTheme.typography.titleMedium,
                                    fontWeight = FontWeight.Bold
                                )
                                Text(text = solicitud.descripcion, style = MaterialTheme.typography.bodyMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Prioridad: ${solicitud.prioridad} | Categoría: ${solicitud.categoria} | Email: ${solicitud.email}",
                                    style = MaterialTheme.typography.bodySmall
                                )

                                Text(
                                    text = "Fecha: ${solicitud.fecha}",
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }
            }

            Button(
                onClick = { navController.popBackStack() },
                modifier = Modifier.fillMaxWidth().padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}
