package ankit.app.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.gestures.snapping.rememberSnapFlingBehavior
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ankit.app.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    UnitConverter(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
@Composable
fun UnitConverter(modifier: Modifier = Modifier) {
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var iExpand by remember { mutableStateOf(false) }
    var oExpand by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.0) }
    val oconversionFactor = remember { mutableStateOf(1.0) }
    fun convertUnits(){
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value*100.0 / oconversionFactor.value).roundToInt()/100.0
        outputValue =result.toString()

    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Unit Converter",
            style = MaterialTheme.typography.headlineLarge
            )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = { inputValue=it
                                                              convertUnits()},
            label = { Text(text = "Enter a Value",
                style = MaterialTheme.typography.labelLarge
                ) },
        )
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            Box {
                Button(onClick = {iExpand=true}) {
                    Text(text = "${inputUnit}")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "ArrowDown"
                    )
                }
            }
            DropdownMenu(expanded = iExpand, onDismissRequest = {iExpand = false}) {
                DropdownMenuItem(
                    text = { Text(text = "Centimeters") },
                    onClick = {
                        iExpand = false
                        inputUnit = "Centimeters"
                        conversionFactor.value=0.01
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Meters") },
                    onClick = {
                        iExpand = false
                        inputUnit = "Meters"
                        conversionFactor.value=1.0
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Feet") },
                    onClick = {
                        iExpand = false
                        inputUnit = "Feet"
                        conversionFactor.value=0.3048
                        convertUnits()
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = "Milimeters") },
                    onClick = {
                        iExpand = false
                        inputUnit = "Milimeters"
                        conversionFactor.value=0.001
                        convertUnits()
                    }
                )
            }
            Spacer(modifier= Modifier.width(16.dp))
            Box {
                Button(onClick = {oExpand=true}) {
                    Text(text = "${outputUnit}")
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "ArrowDown"
                    )
                }
                DropdownMenu(expanded = oExpand, onDismissRequest = {oExpand = false}) {
                    DropdownMenuItem(
                        text = { Text(text = "Centimeters") },
                        onClick = {
                            oExpand = false
                            outputUnit = "Centimeters"
                            oconversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Meters") },
                        onClick = {
                            oExpand = false
                            outputUnit = "Meters"
                            oconversionFactor.value=1.0
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Feet") },
                        onClick = {
                            oExpand = false
                            outputUnit = "Feet"
                            oconversionFactor.value=0.3048
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(
                        text = { Text(text = "Milimeters") },
                        onClick = {
                            oExpand = false
                            outputUnit = "Milimeters"
                            oconversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: ${outputValue} ${outputUnit}",
            style = MaterialTheme.typography.headlineSmall
            )
    }
}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}