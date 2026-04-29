package formulario.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import formulario.domain.model.Solicitud
import formulario.domain.repository.FormularioRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FormularioViewModel @Inject constructor(
    private val repo: FormularioRepository
) : ViewModel() {

    fun enviarSolicitud(solicitud: Solicitud) {
        viewModelScope.launch {
            repo.addSolicitud(solicitud)
        }
    }
}
