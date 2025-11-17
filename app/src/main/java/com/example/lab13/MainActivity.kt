package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Ejercicio4AnimatedContent()
            }
        }
    }
}

enum class PantallaEstado {
    CARGANDO,
    CONTENIDO,
    ERROR
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Ejercicio4AnimatedContent() {

    var estadoActual by remember { mutableStateOf(PantallaEstado.CARGANDO) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Ejercicio 4: AnimatedContent",
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botones para cambiar de estado
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Button(onClick = { estadoActual = PantallaEstado.CARGANDO }) {
                Text("Cargando")
            }
            Button(onClick = { estadoActual = PantallaEstado.CONTENIDO }) {
                Text("Contenido")
            }
            Button(
                onClick = { estadoActual = PantallaEstado.ERROR },
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F)
                )
            ) {
                Text("Error")
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Cambio de contenido con animación
        AnimatedContent(
            targetState = estadoActual,
            transitionSpec = {
                fadeIn(animationSpec = tween(400)) togetherWith
                        fadeOut(animationSpec = tween(400))
            },
            label = "estadoPantalla"
        ) { estado ->

            when (estado) {
                PantallaEstado.CARGANDO -> {
                    Text(
                        text = "Cargando información…",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                PantallaEstado.CONTENIDO -> {
                    Text(
                        text = "Contenido cargado correctamente.",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }

                PantallaEstado.ERROR -> {
                    Text(
                        text = "Ocurrió un error, intenta nuevamente.",
                        style = MaterialTheme.typography.bodyLarge,
                        color = Color(0xFFD32F2F)
                    )
                }
            }
        }
    }
}
