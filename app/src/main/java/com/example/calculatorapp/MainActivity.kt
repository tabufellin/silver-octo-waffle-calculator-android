package com.example.calculatorapp
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import kotlin.properties.Delegates

class MainActivity : ComponentActivity() {

    private lateinit var resultText: TextView
    private var operation: Char = ' '
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var isOperand1Input: Boolean = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.resultText)

        val buttons = arrayOf(
            //basic numbers
            findViewById<Button>(R.id.button0),
            findViewById<Button>(R.id.button1),
            findViewById<Button>(R.id.button2),
            findViewById<Button>(R.id.button3),
            findViewById<Button>(R.id.button4),
            findViewById<Button>(R.id.button5),
            findViewById<Button>(R.id.button6),
            findViewById<Button>(R.id.button7),
            findViewById<Button>(R.id.button8),
            findViewById<Button>(R.id.button9),
            
            //operations
            findViewById<Button>(R.id.buttonAdd),
            findViewById<Button>(R.id.buttonSubtract),
            findViewById<Button>(R.id.buttonMultiply),
            findViewById<Button>(R.id.buttonDivide),
            findViewById<Button>(R.id.buttonClear),
            findViewById<Button>(R.id.buttonEquals),
            findViewById<Button>(R.id.buttonBackspace),


            )

        for (button in buttons) {
            button.setOnClickListener { onButtonClick(it) }
        }

    }




    private fun onButtonClick(view: View) {
        val buttonText = (view as Button).text.toString()
        println("buttonText")

        println(buttonText)
        when {
            buttonText.matches(Regex("[0-9.]")) -> {
                if (isOperand1Input) {
                    updateResultText(buttonText)
                } else {
                    operand2 = operand2 * 10 + buttonText.toDouble()
                    replaceResultText(operand2.toString())
                }
            }
            buttonText == "C" -> {
                clearCalculator()
            }
            buttonText == "=" -> {
                performOperation()
            }
            buttonText == "←" -> {
                println("identify button")
                handleBackspace()
            }
            else -> {
                operation = buttonText[0]
                isOperand1Input = false
                operand1 = resultText.text.toString().toDouble()
                resultText.text = ""
            }
        }
    }
    private fun handleBackspace() {
        if (isOperand1Input) {
            println("if")
            val currentText = resultText.text.toString()
            if (currentText.isNotEmpty()) {
                replaceResultText(currentText.substring(0, currentText.length - 1))
            }
        } else {
            println("else")
            operand2 = (operand2 / 10).toInt().toDouble()
            updateResultText(operand2.toString())
        }
    }

    private fun updateResultText(value: String) {
        resultText.text = "${resultText.text}$value"
    }

    private fun replaceResultText(value: String) {
        resultText.text = value
    }

    private fun clearCalculator() {
        resultText.text = ""
        operand1 = 0.0
        operand2 = 0.0
        operation = ' '
        isOperand1Input = true
    }

    private fun performOperation() {


        when (operation) {

            '+' -> replaceResultText((operand1 + operand2).toString())
            '-' -> replaceResultText((operand1 - operand2).toString())
            '*' -> replaceResultText((operand1 * operand2).toString())
            '/' -> {
                if (operand2 != 0.0) {
                    replaceResultText((operand1 / operand2).toString())
                } else {
                    replaceResultText("Cannot divide by zero")
                }
            }
        }
        println("22222222")

        isOperand1Input = true
        operand1 = resultText.text.toString().toDouble()
        operand2 = 0.0
        operation = ' '
    }


}



