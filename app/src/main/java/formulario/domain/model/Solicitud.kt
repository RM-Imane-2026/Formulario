package formulario.domain.model

data class Solicitud(
    val id: Long,
    val titulo: String = "",
    val descripcion: String = "",
    val categoria: String? = "",
    val prioridad: Long,
    val email: String = "",
    val fecha: String = ""
)
