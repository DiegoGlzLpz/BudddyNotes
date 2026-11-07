package com.PDMD.buddynotes.viewmodel


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Date

data class PantallaInicioUIState(
    val listaTareas: ListaTareas  = ListaTareas(1, Date(),listOf()),
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
            Tarea(3, "Leer",Date(),null),
        ))

    }
}


data class ListaTareas(
    val id_lista:Int,
    val fecha_lista: Date,
    val tareas: List<Tarea>
)
data class Tarea(
    val id_registro: Int,
    val registro: String,
    val fecha_creacion_registro: Date,
    val fecha_fin_registro: Date?
)