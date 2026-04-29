package formulario.domain.repository

import formulario.domain.model.Solicitud

interface FormularioRepository {
    suspend fun getSolicitudes(): List<Solicitud>
    suspend fun addSolicitud(solicitud: Solicitud)
}
