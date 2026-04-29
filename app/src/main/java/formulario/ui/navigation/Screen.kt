package formulario.ui.navigation

sealed class Screen(val route: String) {
    object Inicio : Screen("inicio")
    object Formulario : Screen("formulario")
    object Listado : Screen("listado")
}
