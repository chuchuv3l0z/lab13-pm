package com.example.lab13

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.spring
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
                Ejercicio3SizeAndPosition()
            }
        }
    }
}

@Composable
fun Ejercicio3SizeAndPosition() {

    var isMoved by remember { mutableStateOf(false) }

    // Tamaño animado
    val boxSize by animateDpAsState(
        targetValue = if (isMoved) 180.dp else 100.dp,
        animationSpec = spring(dampingRatio = 0.7f),
        label = "boxSize"
    )

    // Posición animada
    val offsetX by animateDpAsState(
        targetValue = if (isMoved) 80.dp else 0.dp,
        animationSpec = spring(dampingRatio = 0.7f),
        label = "offsetX"
    )

    val offsetY by animateDpAsState(
        targetValue = if (isMoved) 120.dp else 0.dp,
        animationSpec = spring(dampingRatio = 0.7f),
        label = "offsetY"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Button(onClick = { isMoved = !isMoved }) {
            Text(if (isMoved) "Volver a posición inicial" else "Mover y agrandar cuadro")
        }

        Spacer(modifier = Modifier.height(20.dp))

        Box(
            modifier = Modifier
                .offset(x = offsetX, y = offsetY) // posición
                .size(boxSize)                    // tamaño
                .background(Color(0xFFFF5722))    // naranja
        )
    }
}
