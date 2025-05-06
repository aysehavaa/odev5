package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var resultTextView: TextView
    private var firstNumber = 0
    private var operation = ""
    private var isNewNumber = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultTextView = findViewById(R.id.resultTextView)

        // Sayı butonları için click listener'ları ayarla
        val numberButtons = arrayOf(
            R.id.btn0, R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4,
            R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9
        )

        numberButtons.forEach { buttonId ->
            findViewById<Button>(buttonId).setOnClickListener {
                onNumberClick((it as Button).text.toString())
            }
        }

        // Toplama butonu
        findViewById<Button>(R.id.btnPlus).setOnClickListener {
            onOperationClick("+")
        }

        // Temizleme butonu
        findViewById<Button>(R.id.btnClear).setOnClickListener {
            onClearClick()
        }
    }

    private fun onNumberClick(number: String) {
        if (isNewNumber) {
            resultTextView.text = number
            isNewNumber = false
        } else {
            resultTextView.append(number)
        }
    }

    private fun onOperationClick(op: String) {
        firstNumber = resultTextView.text.toString().toInt()
        operation = op
        isNewNumber = true
    }

    private fun onClearClick() {
        resultTextView.text = "0"
        firstNumber = 0
        operation = ""
        isNewNumber = true
    }

    private fun calculateResult() {
        val secondNumber = resultTextView.text.toString().toInt()
        val result = when (operation) {
            "+" -> firstNumber + secondNumber
            else -> secondNumber
        }
        resultTextView.text = result.toString()
        isNewNumber = true
    }
}
