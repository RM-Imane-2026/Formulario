package formulario.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import formulario.ui.theme.FormularioTheme
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest

val supabase = createSupabaseClient(
    supabaseUrl = "https://kfjvikepvpogdvtwtytt.supabase.co/",
    supabaseKey = "sb_publishable_gJfMHMA0ZF4wmvL625BPYA_uXiVa1OH"
){
    install(Postgrest)
}
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            FormularioTheme() {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "login"
                ) {
                    composable ("login") {
                        InicioScreen(navController)
                    }
                    composable(route = "formulario") {
                        SolicitudScreen(
                            navController = navController
                        )
                    }
                    composable(route = "listaformulario") {
                        MisSolicitudesScreen(
                            navController = navController
                        )
                    }
                }
            }
        }
    }
}
