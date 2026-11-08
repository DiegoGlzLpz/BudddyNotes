package com.PDMD.buddynotes.model

import java.text.SimpleDateFormat
import java.util.Locale

//Datos de ejemplo para la pantalla de listado

val LocaleEspaña = Locale("es", "ES")
val FormatterFechas = SimpleDateFormat("dd-MM-yyyy",LocaleEspaña).apply { isLenient=false }

val datosEjemplolistaTareasHistorico = listOf(
    ListaTareas(
        1, FormatterFechas.parse("05-11-2026"),listOf(
            Tarea(1, "Estudiar PMDM",FormatterFechas.parse("05-11-2026"),null),
            Tarea(2, "Programar en Kotlin",FormatterFechas.parse("05-11-2026"),null),
            Tarea(3, "Leer",FormatterFechas.parse("05-11-2026"),FormatterFechas.parse("05-11-2026"))
        )
    ),
    ListaTareas(
        2, FormatterFechas.parse("06-11-2026"),listOf(
            Tarea(1, "Rehacer proyecto BuddyNotes",FormatterFechas.parse("06-11-2026"),FormatterFechas.parse("06-11-2026")),
            Tarea(2, "Hacer pantallaInicio",FormatterFechas.parse("06-11-2026"),FormatterFechas.parse("06-11-2026")),

        )
    ),
    ListaTareas(
        3, FormatterFechas.parse("07-11-2026"),listOf(
            Tarea(1, "Terminar entrega parcial",FormatterFechas.parse("07-11-2026"),null),
            Tarea(2, "Hacer diseños de alta fidelidad",FormatterFechas.parse("07-11-2026"),FormatterFechas.parse("07-11-2026"))
        )
    )
)