package com.example.sorrentinosmoni

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.sorrentinosmoni.ui.theme.SorrentinosMoniTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SorrentinosMoniTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SorrentinosCalculator()
                }
            }
        }
    }
}

@Composable
fun SorrentinosCalculator() {
    var inputText by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculadora de Sorrentinos Tentaciones Moni",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para ingresar la cantidad de sorrentinos
        OutlinedTextField(
            value = inputText,
            onValueChange = { inputText = it },
            label = { Text("Cantidad de sorrentinos") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = {
            val cantidad = inputText.toIntOrNull() ?: 0
            result = calcularIngredientes(cantidad)
        }) {
            Text("Calcular ingredientes")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Mostrar el resultado
        Text(text = result)
    }
}

fun calcularIngredientes(cantidad: Int): String {
    // Proporciones para 32 sorrentinos
    val proporcionesPor32 = 32.0

    // Cálculos para sorrentino de verdura (para 32 sorrentinos)
    val ricotaVerdura = 300.0 * cantidad / proporcionesPor32
    val quesoRalladoVerdura = 50.0 * cantidad / proporcionesPor32
    val espinaca = 100.0 * cantidad / proporcionesPor32

    // Cálculos para sorrentino de ricota y jamón (para 32 sorrentinos)
    val ricotaJamon = 500.0 * cantidad / proporcionesPor32
    val jamonRicota = 200.0 * cantidad / proporcionesPor32

    // Cálculos para sorrentino de jamón y queso (para 32 sorrentinos)
    val queso = 400.0 * cantidad / proporcionesPor32
    val jamonQueso = 200.0 * cantidad / proporcionesPor32

    // Cálculos para la harina (para 32 sorrentinos)
    val harina = 400.0 * cantidad / proporcionesPor32

    return """
        Ingredientes para $cantidad sorrentinos:

        Sorrentino de Verdura:
        - Ricota: $ricotaVerdura g
        - Queso rallado: $quesoRalladoVerdura g
        - Espinaca: $espinaca g

        Sorrentino de Ricota y Jamón:
        - Ricota: $ricotaJamon g
        - Jamón: $jamonRicota g

        Sorrentino de Jamón y Queso:
        - Queso: $queso g
        - Jamón: $jamonQueso g

        Harina (común para todas las recetas):
        - Harina: $harina g
    """.trimIndent()
}

@Preview(showBackground = true)
@Composable
fun SorrentinosCalculatorPreview() {
    SorrentinosMoniTheme {
        SorrentinosCalculator()
    }
}
