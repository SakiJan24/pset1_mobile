

package com.example.nav.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.example.nav.data.CharacterInfo

// Colores para usar en los gradientes animados
val bonusColors = listOf(
    Color(0xFF3A82F8), // Azul
    Color(0xFF52E5E7), // Cian
    Color(0xFF8134AF), // Púrpura
    Color(0xFFE54696)  // Rosa
)

@Composable
fun DetailsScreen(usr: CharacterInfo) {
    val context = LocalContext.current

    val infiniteTransition = rememberInfiniteTransition(label = "details-animation")

    // Animación más lenta para el gradiente de fondo
    val offset by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(3000, easing = LinearEasing), // Más lento para efecto suave
            repeatMode = RepeatMode.Reverse
        ),
        label = "gradient-offset"
    )

    val borderAngle by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(2000, easing = LinearEasing),
            repeatMode = RepeatMode.Restart
        ),
        label = "border-angle"
    )

    // --- Creación de los Pinceles (Brushes) animados ---

    // Gradiente solo en la parte superior (como en la imagen de referencia)
    val backgroundGradientColors = listOf(
        Color(0xFF3A82F8), // Azul vibrante
        Color(0xFF52E5E7), // Cian
        Color(0xFFFFFFFF)  // Blanco (transición a fondo blanco)
    )

    // Gradiente vertical que solo cubre la parte superior
    val backgroundBrush = Brush.verticalGradient(
        colors = backgroundGradientColors,
        startY = offset * 0.2f,
        endY = 800f + offset * 0.3f // Se detiene antes de la mitad de la pantalla
    )

    // Borde con gradiente circular más dinámico
    val borderBrush = Brush.sweepGradient(
        colors = bonusColors + bonusColors.reversed(), // Doble los colores para más transiciones
        center = Offset(100f + borderAngle * 0.5f, 100f + borderAngle * 0.3f)
    )

    // Texto con gradiente más dinámico
    val textBrush = Brush.linearGradient(
        colors = bonusColors,
        start = Offset(offset * 0.8f, offset * 0.6f),
        end = Offset(offset * 1.2f + 300f, offset * 1.1f + 300f),
        tileMode = TileMode.Mirror
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White), // Fondo base blanco
        contentAlignment = Alignment.TopCenter
    ) {
        // Caja para el gradiente solo en la parte superior
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(400.dp) // Solo cubre la parte superior
                .background(backgroundBrush)
        )

        // Contenido encima del gradiente
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(40.dp))

            AsyncImage(
                model = usr.image,
                contentDescription = "Imagen de ${usr.name}",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .shadow(16.dp, CircleShape, clip = true) // Sombra más pronunciada
                    .clip(CircleShape)
                    .border(
                        width = 5.dp, // Borde más grueso
                        brush = borderBrush,
                        shape = CircleShape
                    )
                    .clickable {
                        val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${usr.id}"))
                        context.startActivity(intent)
                    }
            )

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = usr.name,
                style = TextStyle(
                    brush = textBrush,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Card(
                elevation = CardDefaults.cardElevation(8.dp), // Más elevación
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surface // Fondo normal de la tarjeta
                )
            ) {
                Column(Modifier.padding(16.dp)) {
                    InfoRow("Estado:", usr.status)
                    InfoRow("Especie:", usr.species)
                    InfoRow("Género:", usr.gender)
                    InfoRow("Origen:", usr.origin.name)
                    InfoRow("Ubicación:", usr.location.name)
                }
            }
        }
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(modifier = Modifier.padding(vertical = 4.dp)) {
        Text(
            text = label,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.width(100.dp),
            color = MaterialTheme.colorScheme.primary // Color del tema
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.onSurface // Color del tema
        )
    }
}