package com.PDMD.buddynotes.viewmodel
import androidx.lifecycle.ViewModel
import com.PDMD.buddynotes.model.ListaTareas
import com.PDMD.buddynotes.model.Tarea
import com.PDMD.buddynotes.model.datosEjemplolistaTareasHistorico
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.PDMD.buddynotes.model.datosEjemplolistaTareasHistorico as diasAnteriores
import java.util.Date

data class PantallaDetalleHistoricoUIState(
    val id_DetalleHistorico:Int = 2,
    val listaRegistrosDetalle: ListaTareas = ListaTareas(0,Date(),listOf())
){}

private const val TAG = "PantallaDetalleHistoricoVM"

class PantallaDetalleHistoricoVM: ViewModel(){

    private val _uiState = MutableStateFlow(PantallaDetalleHistoricoUIState())
    val uiState: StateFlow<PantallaDetalleHistoricoUIState> = _uiState.asStateFlow();

    fun cargarDetalleHistorico(){
        _uiState.update { //Si la encontramos bien, si no una vacia
            it.copy(listaRegistrosDetalle = diasAnteriores.find{ lda -> lda.id_lista==it.id_DetalleHistorico}?:ListaTareas(0,Date(),listOf()))
        }

    }
}