/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entity

data class DatosUsuario(
    var login: String = "",
    var pwd: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return login.isNotEmpty() && pwd.isNotEmpty()
    }
}
