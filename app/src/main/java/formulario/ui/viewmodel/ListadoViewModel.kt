package formulario.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import formulario.data.model.SolicitudDto
import formulario.domain.model.Solicitud
import formulario.domain.repository.FormularioRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListadoViewModel @Inject constructor(
    private val repo: FormularioRepository
) : ViewModel() {
    private val _solicitudes = MutableStateFlow<List<Solicitud>>(emptyList())
    val solicitudes: StateFlow<List<Solicitud>> = _solicitudes

    init {
        loadSolicitudes()
    }

    fun loadSolicitudes() {
        viewModelScope.launch {
            _solicitudes.value = repo.getSolicitudes()
        }
    }
}
