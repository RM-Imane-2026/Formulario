package formulario.app

import kotlinx.serialization.Serializable

@Serializable
data class Solicitud(
    val id: Int? = null,
    val titulo: String = "",
    val descripcion: String = "",
    val categoria: String = "",
    val prioridad: String = "",
    val email: String = "",
    val fecha: String = ""
)