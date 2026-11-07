package com.PDMD.buddynotes.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.PDMD.buddynotes.viewmodel.PantallaInicioUIState
import com.PDMD.buddynotes.viewmodel.PantallaInicioVM

// 1. Contenedor de la pantalla con estado lógico
@Composable
fun PantallaInicioScreen(
    modifier: Modifier,
    vm: PantallaInicioVM
) {
    val uiState by vm.uiState.collectAsState()

    PantallaInicio(
        uiState = uiState,
        modifier = modifier
    )
}

// 2. Interfaz pura (Stateless) que dibuja tu lista de tareas
@Composable
fun PantallaInicio(
    uiState: PantallaInicioUIState,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        item {
            androidx.compose.material3.Text(text = "Tarea de prueba")
        }
    }

}