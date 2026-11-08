package com.PDMD.buddynotes.viewmodel

import androidx.lifecycle.ViewModel
import com.PDMD.buddynotes.model.ListaTareas
import com.PDMD.buddynotes.model.datosEjemplolistaTareasHistorico as diasAnteriores
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class PantallaHistoricoUIState(
    val filtro:Int =0,
    val listasDiasAnteriores: List<ListaTareas> =emptyList()

){}

private const val TAG = "PantallaHistoricoVM"

class PantallaHistoricoVM: ViewModel(){

    private val _uiState = MutableStateFlow(PantallaHistoricoUIState())
    val uiState: StateFlow<PantallaHistoricoUIState> = _uiState.asStateFlow();

    fun cargarHistoricoTareas(){
        //Datos de ejemplo en la carpeta model
        _uiState.update {
            it.copy(listasDiasAnteriores = diasAnteriores.filter { lda -> lda.id_lista>it.filtro })
        }
    }

}


