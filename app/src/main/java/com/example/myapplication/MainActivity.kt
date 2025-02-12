package com.example.myapplication

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {

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

        // Initialize views
        num1 = findViewById(R.id.number1)
        num2 = findViewById(R.id.number2)
        btnAdd = findViewById(R.id.btn_add)
        btnSub = findViewById(R.id.btn_sub)
        btnDiv = findViewById(R.id.btn_div)
        btnMultiply = findViewById(R.id.btn_multiply)
        resultTextView = findViewById(R.id.answer)
        btnClear = findViewById(R.id.clear)

        // Set listeners using explicit class implementation
        btnAdd.setOnClickListener(AddButtonClickListener("+",this))
        btnSub.setOnClickListener(AddButtonClickListener("-",this))
        btnDiv.setOnClickListener(AddButtonClickListener("/",this))
        btnMultiply.setOnClickListener(AddButtonClickListener("x",this))
        btnClear.setOnClickListener { clearFields() }
    }

    // Explicit OnClickListener class for handling add operation
    class AddButtonClickListener(
        private val operator: String,
        private val activity: MainActivity // Reference to MainActivity
    ) : View.OnClickListener {
        override fun onClick(v: View?) {
            activity.performCalculation(operator) // Call the method on the activity
        }
    }

    private fun performCalculation(operator: String) {
        try {
            // Get input values
            val number1 = num1.text.toString().toDouble()
            val number2 = num2.text.toString().toDouble()
            var result: Double = 0.0

            // Perform calculation based on operator
            when (operator) {
                "+" -> result = number1 + number2
                "-" -> result = number1 - number2
                "x" -> result = number1 * number2
                "/" -> {
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

    private fun clearFields() {
        num1.text.clear()
        num2.text.clear()
        resultTextView.text = ""
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}




