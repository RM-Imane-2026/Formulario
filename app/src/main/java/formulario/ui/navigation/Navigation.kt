package formulario.ui.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import formulario.ui.screen.InicioScreen
import formulario.ui.screen.ListadoScreen
import formulario.ui.screen.FormularioScreen

@Composable
fun Navigation(
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Inicio.route
    ) {

        composable(Screen.Inicio.route) {
            InicioScreen(navController)
        }

        composable(Screen.Formulario.route) {
            FormularioScreen(navController = navController)
        }
        composable(Screen.Listado.route) {
            ListadoScreen(navController = navController)
        }
    }
}
