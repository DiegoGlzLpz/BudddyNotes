package com.PDMD.buddynotes.ui.componente

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.PDMD.buddynotes.R
import com.PDMD.buddynotes.model.FormatterFechas
import com.PDMD.buddynotes.model.ListaTareas
import com.PDMD.buddynotes.model.Tarea
import com.PDMD.buddynotes.ui.theme.BuddyNotesTheme
import java.util.Date
import com.PDMD.buddynotes.model.datosEjemplolistaTareasHistorico as diasAnteriores

data class DetalleHistoricoData(
    val id_lista: Int =2,
    val fecha_lista: Date ,
    val tareas: List<Tarea> = listOf()
)

@Composable
fun DetalleHisticoCard(
    data: DetalleHistoricoData,
    modifier: Modifier = Modifier
){
    val tareasPendientes = data.tareas.count{it.fecha_fin_registro==null}
    val tareasCompletadas = data.tareas.count{it.fecha_fin_registro!=null}
    val tareasTotales = data.tareas.size
    val listaCompletada = (tareasCompletadas == tareasTotales)
    val colorDetalles = if (listaCompletada){
        MaterialTheme.colorScheme.secondaryContainer
    }else{
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val colorLetraDetalles = if (listaCompletada){
        MaterialTheme.colorScheme.onSecondaryContainer
    }else{
        MaterialTheme.colorScheme.onTertiaryContainer
    }
    val iconoTareas =if (listaCompletada){
        painterResource(id = R.drawable.ic_completed_tasks)
    }else{
        painterResource(id = R.drawable.ic_pending_tasks)
    }
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ){
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(all=8.dp)
    ){
        Row(modifier = Modifier
            .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Row(
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_point),
                    contentDescription = "Icono de un reloj",
                    modifier = Modifier
                        .size(8.dp),
                    tint = MaterialTheme.colorScheme.onTertiaryContainer,
                )
                Text(
                    modifier = Modifier.padding(start=4.dp),
                    text = "Tareas Realizadas",
                    textAlign = TextAlign.Start,
                    fontWeight = FontWeight.Bold
                )
            }
            Surface(
                shape = RoundedCornerShape(6.dp),
                color= MaterialTheme.colorScheme.onPrimary.copy(0.3f),
                modifier = Modifier
                    .padding(
                        top=10.dp,
                        bottom = 10.dp
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.onPrimary,
                        shape = RoundedCornerShape(6.dp)
                    ),


            ){
                Box(modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 10.dp,end=10.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text ="Bloqueada",
                        style = MaterialTheme.typography.titleMedium,
                        fontSize = 10.sp,
                        color = MaterialTheme.colorScheme.onPrimary)
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(vertical = 10.dp), // Espacio alrededor de la línea
            thickness = 1.dp,                             // Grosor de la línea
            color = MaterialTheme.colorScheme.outlineVariant // Color opcional adaptado al tema
        )
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(top=5.dp),
            contentPadding = PaddingValues(16.dp,8.dp),
            verticalArrangement = Arrangement.spacedBy(-4.dp)
        ) {
            items(data.tareas) { item ->
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
    }

}

val datosEjemplo : ListaTareas? =diasAnteriores.find{ lda -> lda.id_lista==2}
@Preview
@Composable
fun DetalleHistoricoCardPreview() {
    BuddyNotesTheme() {
        DetalleHisticoCard(

            data = DetalleHistoricoData(datosEjemplo!!.id_lista,datosEjemplo.fecha_lista,datosEjemplo.tareas)
        )
    }
}