package formulario.data.model

import formulario.domain.model.Solicitud
import kotlinx.serialization.Serializable

@Serializable
data class SolicitudDto(
    val id: Long? = null,
    val titulo: String? = null,
    val descripcion: String? = null,
    val categoria: String? = null,
    val prioridad: Long? = null,
    val email: String? = null,
    val fecha: String? = null
)

fun SolicitudDto.toDomain(): Solicitud {
    return Solicitud(
        id = id ?: 0L,
        titulo = titulo ?: "",
        descripcion = descripcion ?: "",
        categoria = categoria ?: "",
        prioridad = prioridad ?: 1L,
        email = email ?: "",
        fecha = fecha ?: ""
    )
}

fun Solicitud.toDto(): SolicitudDto {
    return SolicitudDto(
        id = if (id == 0L) null else id,
        titulo = titulo,
        descripcion = descripcion,
        categoria = categoria,
        prioridad = prioridad,
        email = email,
        fecha = fecha
    )
}
