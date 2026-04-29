package formulario.data.repository

import formulario.data.model.SolicitudDto
import formulario.data.model.toDomain
import formulario.data.model.toDto
import formulario.domain.model.Solicitud
import formulario.domain.repository.FormularioRepository
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.postgrest.query.Order
import javax.inject.Inject

class FormularioRepositoryImpl @Inject constructor(
    private val supabase: SupabaseClient
) : FormularioRepository {

    override suspend fun getSolicitudes(): List<Solicitud> {
        return try {
            val list = supabase.from("solicitudes").select {
                order("fecha", order = Order.DESCENDING)
            }.decodeList<SolicitudDto>()
            list.map { it.toDomain() }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    override suspend fun addSolicitud(solicitud: Solicitud) {
        try {
            val dto = solicitud.toDto()
            supabase.from("solicitudes").insert(dto)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
