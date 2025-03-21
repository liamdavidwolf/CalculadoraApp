package com.example.calculadoraapp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var txtResultado: TextView
    private var numeroActual = ""
    private var operador = ""
    private var numeroAnterior = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        txtResultado = findViewById(R.id.txtResultado)

        val botonesNumeros = listOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        for (id in botonesNumeros) {
            findViewById<Button>(id).setOnClickListener { botonPresionado((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btnSum).setOnClickListener { operadorPresionado("+") }
        findViewById<Button>(R.id.btnRes).setOnClickListener { operadorPresionado("-") }
        findViewById<Button>(R.id.btnMul).setOnClickListener { operadorPresionado("*") }
        findViewById<Button>(R.id.btnDiv).setOnClickListener { operadorPresionado("/") }

        findViewById<Button>(R.id.btnIgual).setOnClickListener { calcularResultado() }
        findViewById<Button>(R.id.btnC).setOnClickListener { limpiar() }
    }

    private fun botonPresionado(numero: String) {
        numeroActual += numero
        txtResultado.text = numeroActual
    }

    private fun operadorPresionado(op: String) {
        if (numeroActual.isNotEmpty()) {
            numeroAnterior = numeroActual
            numeroActual = ""
            operador = op
        }
    }

    private fun calcularResultado() {
        if (numeroActual.isNotEmpty() && numeroAnterior.isNotEmpty()) {
            val num1 = numeroAnterior.toDouble()
            val num2 = numeroActual.toDouble()
            val resultado = when (operador) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> if (num2 != 0.0) num1 / num2 else "Error"
                else -> "Error"
            }
            txtResultado.text = resultado.toString()
            numeroActual = resultado.toString()
            numeroAnterior = ""
        }
    }

    private fun limpiar() {
        numeroActual = ""
        numeroAnterior = ""
        operador = ""
        txtResultado.text = "0"
    }
}