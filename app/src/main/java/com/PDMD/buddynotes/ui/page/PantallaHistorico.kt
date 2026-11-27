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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.PDMD.buddynotes.R
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
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment =Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .padding(top=10.dp),
            text= stringResource(R.string.historialDeNotas_PantallaHistorico),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.8f),
            text="Consulta las notas de días anteriores",
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Center
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.90f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(uiState.listasDiasAnteriores,key = {it.id_lista}) {
                listaDia ->
                ListaTareasCard(
                    data = ListaTareasData(listaDia.id_lista,listaDia.fecha_lista,listaDia.tareas),
                    onDetallesClick = {
                        onListaClick(listaDia.id_lista)
                    }
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