package com.PDMD.buddynotes.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.PDMD.buddynotes.ui.componente.ListaTareasCard
import com.PDMD.buddynotes.ui.componente.ListaTareasData
import com.PDMD.buddynotes.ui.theme.Background
import com.PDMD.buddynotes.ui.theme.BuddyNotesTheme
import com.PDMD.buddynotes.viewmodel.PantallaHistoricoVM

@Composable
fun PantallaHistorico(
   onListaClick: (Int)->Unit,
   modifier: Modifier = Modifier,
   viewModel: PantallaHistoricoVM = viewModel()
){
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.cargarHistoricoTareas()

    }
    Column(
        modifier=modifier
            .fillMaxWidth()
            .background(Background),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start=24.dp,top=15.dp),
            text="Historial de Notas",
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start=24.dp)
            ,
            text="Consulta las notas de días anteriores",
            style = MaterialTheme.typography.bodySmall
        )

        LazyColumn(
            contentPadding = PaddingValues(all=20.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(uiState.listasDiasAnteriores,key = {it.id_lista}) {
                listaDia ->
                ListaTareasCard(
                    data = ListaTareasData(listaDia.id_lista,listaDia.fecha_lista,listaDia.tareas),
                    onClick= { onListaClick(listaDia.id_lista)}
                )
            }
        }

    }
}
@Preview
@Composable
fun PantallaHistoricoPreview() {
    BuddyNotesTheme() {
        PantallaHistorico(onListaClick = {})
    }
}