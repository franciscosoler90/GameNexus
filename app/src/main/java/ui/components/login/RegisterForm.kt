/*
 * Copyright (c) 2023. Francisco José Soler Conchello
 */

package ui.components.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.fransoler.R
import entidades.DatosUsuario
import interfaces.LoginInterface

@Composable
fun RegisterForm(loginCallbacks: LoginInterface) {
    Surface {
        var datosUsuario by remember { mutableStateOf(DatosUsuario()) }
        val iconPainter = painterResource(id = R.drawable.ic_launcher_round)

        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 120.dp)
        ) {
            Image(
                painter = iconPainter,
                contentDescription = null,
                modifier = Modifier.size(144.dp)
            )
            Text(
                text = "Registrarte",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(20.dp))

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                NameField(
                    value = datosUsuario.name,
                    onChange = { data -> datosUsuario = datosUsuario.copy(name = data) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                EmailField(
                    value = datosUsuario.email,
                    onChange = { data -> datosUsuario = datosUsuario.copy(email = data) },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(modifier = Modifier.height(10.dp))
                PasswordField(
                    value = datosUsuario.pwd,
                    onChange = { data -> datosUsuario = datosUsuario.copy(pwd = data) },
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(20.dp))
            Button(
                onClick = {
                    loginCallbacks.createAccount(datosUsuario)
                },
                enabled = datosUsuario.isNotEmpty(),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Registrarte")
            }
            Spacer(modifier = Modifier.height(20.dp))
            Text(
                text = "¿Ya tienes una cuenta?",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.clickable {
                    loginCallbacks.loginActivity()
                }
            )

        }
    }
}