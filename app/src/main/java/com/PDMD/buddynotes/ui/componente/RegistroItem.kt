package com.PDMD.buddynotes.ui.componente

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardDefaults.cardElevation
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxColors
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Date


data class RegistroItemData(
    val id_registro:Int,
    val registro:String,
    val fecha_inicio_registro: Date,
    var fecha_fin_registro:Date?
)

@Composable
fun RegistroItem(data: RegistroItemData,modifier: Modifier = Modifier){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 3.dp)
            ,colors = CardDefaults.cardColors(
            containerColor = Color.Transparent // <-- Esto elimina el color de fondo de la card
        )

    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,


        ){
            Checkbox( modifier = Modifier,
                checked = data.fecha_fin_registro != null,
                onCheckedChange = { data.fecha_fin_registro = Date() },
                colors = CheckboxDefaults.colors(
                    MaterialTheme.colorScheme.onTertiaryContainer,
                        )


            )
            val tareaCompletada =if(data.fecha_fin_registro != null){TextDecoration.LineThrough}else{TextDecoration.None}
            Text(modifier= Modifier
                    .padding(start= 8.dp)
                .weight(1f) ,
                text = data.registro,
                textDecoration = tareaCompletada
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