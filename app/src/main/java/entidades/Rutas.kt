/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entidades

sealed class Routes(val route: String) {
    object Login : Routes("Login")
}