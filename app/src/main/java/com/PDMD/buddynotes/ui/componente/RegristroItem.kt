package com.PDMD.buddynotes.ui.componente

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import java.util.Date


data class RegistroItemData(
    val id_registro:Int,
    val registro:String,
    val fecha_inicio_registro: Date,
    var fecha_fin_registro:Date?
)

@Composable
fun RegistroItem(data: RegistroItemData,modifier: Modifier = Modifier){
    var checked by remember { mutableStateOf(true) }
    Card(
        modifier = modifier.fillMaxWidth(),
        elevation = cardElevation(defaultElevation = 2.dp)
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,


        ){
            Text(modifier= Modifier
                    .padding(start= 8.dp)
                .weight(1f) ,
                text = data.registro
            )
            Checkbox( modifier = Modifier,
                checked = data.fecha_fin_registro != null,
                onCheckedChange = { data.fecha_fin_registro = Date() }

            )
        }
    }
}

@Preview
@Composable
fun RegistroItemPreview() {
    MaterialTheme() {
        RegistroItem(
            data = RegistroItemData(
                id_registro = 0,
                registro = "Registro de ejemplo",
                fecha_inicio_registro = Date(),
                fecha_fin_registro = null

            )
        )
    }


}