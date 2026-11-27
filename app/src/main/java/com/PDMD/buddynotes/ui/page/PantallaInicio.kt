package com.PDMD.buddynotes.ui.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import com.PDMD.buddynotes.ui.componente.RegistroItem
import com.PDMD.buddynotes.ui.componente.RegistroItemData
import com.PDMD.buddynotes.ui.theme.BuddyNotesTheme
import com.PDMD.buddynotes.viewmodel.PantallaInicioVM

@Composable
fun PantallaInicio(
    modifier: Modifier = Modifier,
    vm: PantallaInicioVM = viewModel()
) {
    var state = vm.uiState.collectAsState()

    LaunchedEffect(Unit) {
        vm.dailyTasksLoad()
    }
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment =Alignment.CenterHorizontally
    ){
        Text(
            modifier=Modifier
                .fillMaxWidth(0.8f)
                .padding(vertical =10.dp),
            text = "Tareas para hoy:",
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            textDecoration = TextDecoration.Underline

        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize(0.9f),
            contentPadding = PaddingValues(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(state.value.tareasHoy) { item ->
                RegistroItem(
                    RegistroItemData(
                    id_registro = item.id_registro,
                    registro = item.registro,
                    fecha_inicio_registro = item.fecha_creacion_registro,
                    fecha_fin_registro = item.fecha_fin_registro
                    ),
                    onCheckedChange={checked -> vm.updateTaskStatus(item.id_registro,checked)}
                )
            }
        }
    }

}
@Preview
@Composable
fun PantallaInicioPreview() {
    BuddyNotesTheme() {
        PantallaInicio()
    }
}