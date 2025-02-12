package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

        // Declare the UI elements
        lateinit var num1: EditText
        lateinit var num2: EditText
        lateinit var btnAdd: Button
        lateinit var btnSub: Button
        lateinit var btnDiv: Button
        lateinit var btnMultiply: Button
        lateinit var resultTextView: TextView
        lateinit var btnClear: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the UI elements
        num1 = findViewById(R.id.number1)
        num2 = findViewById(R.id.number2)
        btnAdd = findViewById(R.id.btn_add)
        btnSub = findViewById(R.id.btn_sub)
        btnDiv = findViewById(R.id.btn_div)
        btnMultiply = findViewById(R.id.btn_multiply)
        resultTextView = findViewById(R.id.answer)
        btnClear = findViewById(R.id.clear)

        // Set click listeners for each button
        btnAdd.setOnClickListener { performCalculation(Operation.ADD) }
        btnSub.setOnClickListener { performCalculation(Operation.SUBTRACT) }
        btnDiv.setOnClickListener { performCalculation(Operation.DIVIDE) }
        btnMultiply.setOnClickListener { performCalculation(Operation.MULTIPLY) }
        btnClear.setOnClickListener { clearFields() }
    }

    // Enum to represent the type of operation
    enum class Operation {
        ADD, SUBTRACT, MULTIPLY, DIVIDE
    }

    // Perform calculation based on selected operation
    private fun performCalculation(operation: Operation) {
        try {
            // Get input values
            val number1 = num1.text.toString().toDouble()
            val number2 = num2.text.toString().toDouble()
            var result: Double

            // Perform the selected operation
            when (operation) {
                Operation.ADD -> result = number1 + number2
                Operation.SUBTRACT -> result = number1 - number2
                Operation.MULTIPLY -> result = number1 * number2
                Operation.DIVIDE -> {
                    if (number2 != 0.0) {
                        result = number1 / number2
                    } else {
                        showToast("Cannot divide by zero!")
                        return
                    }
                }
            }

            // Display the result
            resultTextView.text = result.toString()

        } catch (e: NumberFormatException) {
            showToast("Please enter valid numbers!")
        }
    }

    // Clear input fields and the result
    private fun clearFields() {
        num1.text.clear()
        num2.text.clear()
        resultTextView.text = ""
    }

    // Show a toast message
    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}



