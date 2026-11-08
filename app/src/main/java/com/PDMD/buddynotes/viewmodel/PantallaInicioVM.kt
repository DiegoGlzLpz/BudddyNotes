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

    fun load(){
        _uiState.value = _uiState.value.copy(tareasHoy = listOf(
            Tarea(1, "Estudiar",Date(),null),
            Tarea(2, "Programar en Kotlin",Date(),null),
            Tarea(3, "Leer",Date(),Date()),
        ))

    }
}