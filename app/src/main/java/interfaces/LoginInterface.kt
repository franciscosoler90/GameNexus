/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package interfaces

import entidades.DatosUsuario

interface LoginInterface {
    fun onLoginClicked(datosUsuario: DatosUsuario)
    // Otros métodos para eventos de inicio de sesión, registro, etc., según sea necesario
}