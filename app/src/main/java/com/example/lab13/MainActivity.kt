package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MaterialTheme {
                Ejercicio2ColorAnimation()
            }
        }
    }
}

@Composable
fun Ejercicio2ColorAnimation() {

    var isBlue by remember { mutableStateOf(true) }

    // Animación del color
    val animatedColor by animateColorAsState(
        targetValue = if (isBlue) Color(0xFF2196F3) else Color(0xFFE91E63),
        animationSpec = tween(durationMillis = 600),
        label = "colorAnimation"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // Botón para cambiar el color
        Button(onClick = { isBlue = !isBlue }) {
            Text("Cambiar color")
        }

        Spacer(modifier = Modifier.height(20.dp))

        // Cuadro que cambia de color suavemente
        Box(
            modifier = Modifier
                .size(150.dp)
                .background(animatedColor)
        )
    }
}
