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
import com.google.firebase.auth.UserProfileChangeRequest
import entidades.DatosUsuario
import interfaces.LoginInterface
import ui.components.login.RegisterForm
import ui.theme.AppTheme

class RegisterActivity : ComponentActivity(), LoginInterface {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        auth = FirebaseAuth.getInstance()

        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    RegisterForm(this@RegisterActivity)
                }
            }
        }
    }

    override fun loginActivity() {
        val intent = Intent(baseContext, LoginActivity::class.java)
        startActivity(intent)
    }

    override fun createAccount(datosUsuario: DatosUsuario) {
        auth.createUserWithEmailAndPassword(datosUsuario.email, datosUsuario.pwd)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    updateName(datosUsuario)
                } else {
                    Toast.makeText(baseContext, R.string.errorAuth, Toast.LENGTH_SHORT).show()
                }
            }
    }

    override fun updateName(datosUsuario: DatosUsuario) {
        val user = auth.currentUser
        val profileUpdates = UserProfileChangeRequest.Builder().setDisplayName(datosUsuario.name).build()

        user?.updateProfile(profileUpdates)
            ?.addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // El nombre de usuario se ha actualizado exitosamente
                    // Autenticación exitosa, redirige a MainActivity
                    val intent = Intent(baseContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    // Hubo un error al actualizar el nombre de usuario
                    // Autenticación fallida, muestra un Toast
                    Toast.makeText(baseContext, R.string.errorUpdate, Toast.LENGTH_SHORT).show()
                }
            }

    }

    override fun registerActivity() {
        //No hace nada
    }

    override fun signIn(datosUsuario: DatosUsuario) {
        //No hace nada
    }

}