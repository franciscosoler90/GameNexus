/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package utilidades

import entidades.ConverterDate
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun String.convertDateTo(
    formatoDeseado: ConverterDate,
    formatoOriginal: ConverterDate = ConverterDate.SQL_DATE
): String? {
    return if (this.isNotEmpty()) {

        return try {
            val formatoFechaOriginal = SimpleDateFormat(formatoOriginal.formatter, Locale.getDefault())
            val fecha = formatoFechaOriginal.parse(this) ?: Date()

            val formatoFechaDeseado = SimpleDateFormat(formatoDeseado.formatter, Locale("es", "ES"))
            formatoFechaDeseado.format(fecha)
        }catch (e : Exception){
            null
        }

    } else {
        "-"
    }
}
