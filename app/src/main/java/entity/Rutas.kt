/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package entity

sealed class Routes(val route: String) {
    object Login : Routes("Login")
}