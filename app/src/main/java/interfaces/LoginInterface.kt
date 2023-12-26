/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.DatosUsuario

interface LoginInterface {
    fun onLoginClicked(datosUsuario: DatosUsuario)

    fun registerActivity()

    fun loginActivity()
}