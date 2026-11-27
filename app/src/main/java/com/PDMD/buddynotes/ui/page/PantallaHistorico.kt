package com.PDMD.buddynotes.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.PDMD.buddynotes.R
import com.PDMD.buddynotes.ui.componente.ProgressDetailCard
import com.PDMD.buddynotes.ui.componente.ProgressDetailCardData
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
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment =Alignment.CenterHorizontally
    ){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(start = 5.dp, top = 10.dp),
            text= stringResource(R.string.historialDeNotas_PantallaHistorico),
            style = MaterialTheme.typography.titleLarge,
            textDecoration = TextDecoration.Underline,
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(start = 5.dp),
            text= stringResource(R.string.description_PantallaHistorico),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start
        )

        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(0.90f),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ){
            items(uiState.listasDiasAnteriores,key = {it.id_lista}) {
                listaDia ->
                ProgressDetailCard(
                    data = ProgressDetailCardData(
                        listaDia.id_lista,
                        listaDia.fecha_lista,
                        stringResource(R.string.descripcion_ListaTareasCard),
                        listaDia.tareas.size,
                        listaDia.tareas.count { it.fecha_fin_registro !=null }
                    ),
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