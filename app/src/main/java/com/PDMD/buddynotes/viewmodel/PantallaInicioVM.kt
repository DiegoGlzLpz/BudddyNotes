package com.PDMD.buddynotes.viewmodel


import androidx.lifecycle.ViewModel
import com.PDMD.buddynotes.model.Tarea
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Date

data class PantallaInicioUIState(
    val tareasHoy: List<Tarea> = listOf()
){}

private const val TAG = "PantallaInicioVM"

class PantallaInicioVM: ViewModel(){

    private val _uiState = MutableStateFlow(PantallaInicioUIState())
    val uiState = _uiState.asStateFlow();

    fun dailyTasksLoad(){
        _uiState.value = _uiState.value.copy(tareasHoy = listOf(
            Tarea(1, "Estudiar",Date(),null),
            Tarea(2, "Programar en Kotlin",Date(),null),
            Tarea(3, "Leer",Date(),Date()),
        ))

    }
    fun updateTaskStatus(idRegistro: Int, checked: Boolean) {
        _uiState.update { state ->
            state.copy(
                tareasHoy = state.tareasHoy.map { tarea ->
                    if (tarea.id_registro == idRegistro) {
                        tarea.copy(
                            fecha_fin_registro = if (checked) Date() else null
                        )
                    } else {
                        tarea
                    }
                }
            )
        }
    }
}