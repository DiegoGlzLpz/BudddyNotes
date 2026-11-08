package com.PDMD.buddynotes.model

import java.util.Date

data class ListaTareas(
    val id_lista:Int,
    val fecha_lista: Date,
    val tareas: List<Tarea>
)