/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

data class DatosUsuario(
    var email: String = "",
    var pwd: String = "",
    var remember: Boolean = false
) {
    fun isNotEmpty(): Boolean {
        return email.isNotEmpty() && pwd.isNotEmpty()
    }
}
