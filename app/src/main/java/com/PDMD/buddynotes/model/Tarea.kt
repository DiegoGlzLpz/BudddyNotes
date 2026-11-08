package com.PDMD.buddynotes.model

import java.util.Date

data class Tarea(
    val id_registro: Int,
    val registro: String,
    val fecha_creacion_registro: Date,
    val fecha_fin_registro: Date?
)