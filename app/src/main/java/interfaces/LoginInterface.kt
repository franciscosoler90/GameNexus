/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.DatosUsuario

interface LoginInterface {
    fun signIn(datosUsuario: DatosUsuario)

    fun createAccount(datosUsuario: DatosUsuario)

    fun updateName(datosUsuario: DatosUsuario)

    fun registerActivity()

    fun loginActivity()
}