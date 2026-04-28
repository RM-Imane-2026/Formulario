package formulario.app

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun InicioScreen(
    navigator: NavController
) {
    Column(
        modifier = Modifier.padding(top = 50.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Inicio Screen")

        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) { Button(onClick = {
            navigator.navigate(
                "formulario"
            )
        }) {
            Text("Crear solicitud")
        }

            Button(onClick = {
                navigator.navigate(
                    "listaformulario"
                )
            }) {
                Text("Mis Solicitudes")
            }}

    }
}
