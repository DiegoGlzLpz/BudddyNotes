package com.PDMD.buddynotes.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import com.PDMD.buddynotes.ui.componente.RegistroItem
import com.PDMD.buddynotes.ui.componente.RegistroItemData
import com.PDMD.buddynotes.viewmodel.PantallaInicioUIState
import com.PDMD.buddynotes.viewmodel.PantallaInicioVM

@Composable
fun PantallaInicio(
    modifier: Modifier = Modifier,
    vm: PantallaInicioVM = viewModel()
) {
    var state = vm.uiState.collectAsState()

    LaunchedEffect(Unit) {
        vm.load()
    }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(top=30.dp),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(state.value.tareasHoy) { item ->
            RegistroItem(
                RegistroItemData(
                id_registro = item.id_registro,
                registro = item.registro,
                fecha_inicio_registro = item.fecha_creacion_registro,
                fecha_fin_registro = item.fecha_fin_registro
                )
            )
        }
    }

}
@Preview
@Composable
fun PantallaInicioPreview() {
    MaterialTheme() {
        PantallaInicio()
    }
}