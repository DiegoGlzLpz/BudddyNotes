package com.PDMD.buddynotes.ui.page

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.ui.unit.dp
import com.PDMD.buddynotes.R
import com.PDMD.buddynotes.model.FormatterFechas
import com.PDMD.buddynotes.ui.componente.DetalleHisticoCard
import com.PDMD.buddynotes.ui.componente.DetalleHistoricoData
import com.PDMD.buddynotes.ui.theme.Background
import com.PDMD.buddynotes.ui.theme.BuddyNotesTheme
import com.PDMD.buddynotes.viewmodel.PantallaDetalleHistoricoVM

@Composable
fun PantallaDetalleHistorico(
    modifier: Modifier = Modifier,
    vm: PantallaDetalleHistoricoVM = viewModel(),
    idDia: Int
) {
    var state = vm.uiState.collectAsState()
    var datos =state.value.listaRegistrosDetalle

    LaunchedEffect(idDia) {
        vm.cargarDetalleHistorico(idDia)
    }
    Column(modifier=modifier
        .fillMaxWidth()
        .background(MaterialTheme.colorScheme.background),
        horizontalAlignment =Alignment.CenterHorizontally
        ){
        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(start = 5.dp, top = 10.dp),
            text=FormatterFechas.format(datos.fecha_lista),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            textDecoration = TextDecoration.Underline
        )
        Text(
            modifier = Modifier
                .fillMaxWidth(0.9f)
                .padding(start = 5.dp,bottom = 15.dp),
            text= stringResource(R.string.description_PantallaDetalleHistorico),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.Start
        )
        DetalleHisticoCard(
            data= DetalleHistoricoData(datos.id_lista,datos.fecha_lista,datos.tareas),
            modifier = Modifier.fillMaxWidth(0.9f),
        )
    }

}
@Preview
@Composable
fun PantallaDetalleHistoricoPreview() {
    BuddyNotesTheme() {
        PantallaDetalleHistorico(idDia = 3)
    }
}