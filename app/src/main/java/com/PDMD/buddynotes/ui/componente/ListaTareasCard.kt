package com.PDMD.buddynotes.ui.componente


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.PDMD.buddynotes.R
import com.PDMD.buddynotes.model.FormatterFechas
import com.PDMD.buddynotes.model.Tarea
import com.PDMD.buddynotes.ui.theme.BuddyNotesTheme
import java.util.Date

data class ListaTareasData(
    val id_lista: Int ,
    val fecha_lista: Date ,
    val tareas: List<Tarea> = listOf()
)

@Composable
fun ListaTareasCard(
    data: ListaTareasData,
    onClick:() ->Unit,
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
    Card(modifier = modifier
        .fillMaxWidth()
        .clickable(onClick = onClick),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ){
        Column(
            modifier=Modifier
                .fillMaxWidth()
                .padding(start=30.dp, top=15.dp, end=30.dp, bottom = 15.dp)
                ,
            verticalArrangement = Arrangement.Center

        ){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ){
                Column(modifier = Modifier.weight(1f)){
                    Text(
                        text =FormatterFechas.format(data.fecha_lista),
                        textAlign = TextAlign.Start,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                    Text(text="Descripcion",
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f)
                    )

                }
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color= colorDetalles,
                    modifier = Modifier
                        .padding(
                            top=10.dp,
                            bottom = 10.dp
                        )
                        .border(
                            width = 1.dp,
                            color = colorLetraDetalles,
                            shape = RoundedCornerShape(6.dp)
                        )

                ){
                    Box(modifier = Modifier
                            .wrapContentWidth()
                            .padding(start = 10.dp,end=10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text ="$tareasCompletadas de $tareasTotales",
                            style = MaterialTheme.typography.titleMedium,
                            color = colorLetraDetalles)
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Row(modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(6.dp))
                        .background(color = MaterialTheme.colorScheme.background)
                        .padding(10.dp,10.dp)
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = iconoTareas,
                            contentDescription = "Icono de un reloj",
                            modifier = Modifier
                                .size(26.dp)
                                .padding(end=10.dp),
                            tint = colorLetraDetalles,
                        )
                        Text(
                            text = "$tareasCompletadas tareas completadas",
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            fontSize = 12.sp
                        )
                    }
                    Text(
                        text ="$tareasPendientes pendiente",
                        modifier = Modifier,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        color = colorLetraDetalles
                        )
                }
                LinearProgressIndicator(
                    progress = {tareasCompletadas.toFloat()/tareasTotales.toFloat()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top=20.dp,bottom=5.dp),
                    color = colorLetraDetalles,
                    trackColor = colorDetalles,
                    strokeCap = StrokeCap.Round
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top=5.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(onClick = { },
                        modifier = Modifier.height(30.dp),
                    ) {
                        Icon(
                           painter = painterResource(id = R.drawable.ic_details_icon),
                           contentDescription = "Icono de un reloj",
                           modifier = Modifier,
                           tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f)
                        )
                        Spacer(modifier= Modifier.width(8.dp))
                        Text(
                            modifier = Modifier,
                            text = "Ver detalles",
                            fontSize = 9.sp,
                            lineHeight = 9.sp,//Tuve que darle el mismo tamaño de linea porque si no se desfasaba
                            textAlign = TextAlign.Start,
                            color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f)
                        )
                    }
                }
            }


        }
    }
}

@Preview
@Composable
fun ListaTareasCardPreview() {
    BuddyNotesTheme(){
        ListaTareasCard(
            data = ListaTareasData(
                id_lista = 0,
                fecha_lista = FormatterFechas.parse("07-11-2026"),
                tareas = listOf(
                    Tarea(1, "Estudiar", Date(), null),
                    Tarea(2, "Programar en Kotlin", Date(), Date()),
                    Tarea(3, "Leer", Date(), Date()),
                )
            ),
            onClick = {}
        )
    }


}