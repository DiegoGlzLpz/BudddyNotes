package com.PDMD.buddynotes.ui.componente


import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.ui.res.stringResource
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

data class ProgressDetailCardData(
    val id: Int ,
    val title: Date ,
    val subtitle: String,
    val total: Int,
    val completed : Int
)

@Composable
fun ProgressDetailCard(
    data: ProgressDetailCardData,
    onDetallesClick:() ->Unit,
    modifier: Modifier = Modifier
){
    val pending = data.total-data.completed
    val completed = data.completed
    val total = data.total
    val progressCompleted = (completed == total)
    val detailColor = if (progressCompleted){
        MaterialTheme.colorScheme.secondaryContainer
    }else{
        MaterialTheme.colorScheme.tertiaryContainer
    }
    val detailTextColor = if (progressCompleted){
        MaterialTheme.colorScheme.onSecondaryContainer
    }else{
        MaterialTheme.colorScheme.onTertiaryContainer
    }
    val progressIcon =if (progressCompleted){
        painterResource(id = R.drawable.ic_completed_tasks)
    }else{
        painterResource(id = R.drawable.ic_pending_tasks)
    }
    Card(modifier = modifier
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.inverseOnSurface,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant
        )
    ){
        Column(
            modifier=Modifier
                .fillMaxWidth()
                .padding(start = 30.dp, top = 15.dp, end = 30.dp, bottom = 15.dp)
                ,
            verticalArrangement = Arrangement.Center

        ){
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 20.dp)
            ){
                Column(modifier = Modifier.weight(1f)){
                    Text(
                        text =FormatterFechas.format(data.title),
                        textAlign = TextAlign.Start,
                        textDecoration = TextDecoration.Underline,
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(text= data.subtitle,
                        modifier = Modifier
                            .fillMaxWidth(0.8f),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f)
                    )

                }
                Surface(
                    shape = RoundedCornerShape(6.dp),
                    color= detailColor,
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            bottom = 10.dp
                        )
                        .border(
                            width = 1.dp,
                            color = detailTextColor,
                            shape = RoundedCornerShape(6.dp)
                        )

                ){
                    Box(modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 10.dp, end = 10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(text = stringResource(
                            R.string.resumenTareas_LIstaTareasCard,
                            completed,
                            total
                        ),
                            style = MaterialTheme.typography.titleMedium,
                            color = detailTextColor)
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
                    .background(color = MaterialTheme.colorScheme.primary)
                    .padding(10.dp, 10.dp)
                        ,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    ){
                    Row(
                        modifier = Modifier,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = progressIcon,
                            contentDescription = stringResource(R.string.watchIcon_ListaTareasCard),
                            modifier = Modifier
                                .size(26.dp)
                                .padding(end = 10.dp),
                            tint = detailTextColor,
                        )
                        Text(
                            text = stringResource(
                                R.string.tareasCompletadas_ListaTareasCard,
                                completed
                            ),
                            modifier = Modifier,
                            textAlign = TextAlign.Start,
                            fontSize = 12.sp
                        )
                    }
                    Text(
                        text = stringResource(
                            R.string.tareasPendientes_ListaTareasCard,
                            pending
                        ),
                        modifier = Modifier,
                        fontSize = 12.sp,
                        textAlign = TextAlign.End,
                        color = detailTextColor
                        )
                }
                LinearProgressIndicator(
                    progress = {completed.toFloat()/total.toFloat()},
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 5.dp),
                    color = detailTextColor,
                    trackColor = detailColor,
                    strokeCap = StrokeCap.Round
                )
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp),
                    horizontalArrangement = Arrangement.End,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    Button(onClick = onDetallesClick,
                        modifier = Modifier.height(30.dp),
                    ) {
                        Icon(
                           painter = painterResource(id = R.drawable.ic_details_icon),
                           contentDescription = stringResource(R.string.eyeIcon_ListaTareasCard),
                           modifier = Modifier,
                           tint = MaterialTheme.colorScheme.onSurfaceVariant.copy(0.7f)
                        )
                        Spacer(modifier= Modifier.width(8.dp))
                        Text(
                            modifier = Modifier,
                            text = stringResource(R.string.botonDetalles_ListaTareasCard),
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
        ProgressDetailCard(
            data = ProgressDetailCardData(
                id = 0,
                title = FormatterFechas.parse("07-11-2026"),
                subtitle = stringResource(R.string.descripcion_ListaTareasCard),
                total = 4,
                completed = 2
            ),
            onDetallesClick = {}
        )
    }


}