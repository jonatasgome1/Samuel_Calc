import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.pow
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompoundInterestCalculator() {
    var principal by remember { mutableStateOf(0.0) }
    var rate by remember { mutableStateOf(0.0) }
    var time by remember { mutableStateOf(0) }

    // Use mutableStateOf para result e inicialize com 0.0
    var result by remember { mutableStateOf(0.0) }
    var compoundInterestRate by remember { mutableStateOf(0.0) }

    var calculatePressed by remember { mutableStateOf(false) } // Controla se o botão foi pressionado

    val h5Style = TextStyle(
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    val h6Style = TextStyle(
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )

    // Função para calcular o resultado
    fun calculateInterest() {
        val principalAmount = principal
        val annualRate = rate / 100
        val numberOfTimesCompounded = 1 // Compounded annually
        val timeInYears = time

        val amount = principalAmount * (1 + annualRate / numberOfTimesCompounded).pow(numberOfTimesCompounded * timeInYears)

        val compoundInterest = amount - principalAmount

        result = amount
        compoundInterestRate = compoundInterest

        calculatePressed = true
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Calculadora de juros compostos",
            style = h5Style,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        TextField(
            value = principal.toString(),
            onValueChange = {
                principal = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text("Quantia principal") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )

        TextField(
            value = rate.toString(),
            onValueChange = {
                rate = it.toDoubleOrNull() ?: 0.0
            },
            label = { Text("Taxa de juros anual (%)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
        )

        TextField(
            value = time.toString(),
            onValueChange = {
                time = it.toIntOrNull() ?: 0
            },
            label = { Text("Tempo (em anos)") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
        )

        if (calculatePressed) {
            val formattedResult = "%.2f".format(result).take(6)
            Text(
                text = "Quantia Final: $formattedResult",
                style = h6Style,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
            )

            Text(
                text = "Juro Composto da Taxa de Juros Anual: $compoundInterestRate",
                style = h6Style,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                calculateInterest()
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
        ) {
            Text("Calcular")
        }
    }
}
