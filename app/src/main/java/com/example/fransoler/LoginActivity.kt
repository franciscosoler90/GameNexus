/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package com.example.fransoler

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.firebase.auth.FirebaseAuth
import entidades.DatosUsuario
import interfaces.LoginInterface
import ui.components.LoginForm
import ui.theme.AppTheme

class LoginActivity : ComponentActivity(), LoginInterface {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContent {
            val loginInterface = this@LoginActivity // Accede a la instancia de la actividad
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LoginForm(loginInterface)
                }
            }
        }
    }

    //Método al hacer clic en el botón Entrar
    override fun onLoginClicked(datosUsuario: DatosUsuario) {
        auth.signInWithEmailAndPassword(datosUsuario.login, datosUsuario.pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Autenticación exitosa, redirige a MainActivity
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Autenticación fallida, muestra un Toast
                    Toast.makeText(this, "Error de inicio de sesión", Toast.LENGTH_LONG).show()
                }
            }
    }

}