/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

enum class ConverterDate(val formatter: String) {
    FULL_DATE("dd MMMM yyyy"),
    SQL_DATE("yyyy-MM-dd"),
}
