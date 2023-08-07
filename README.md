# silver-octo-waffle-calculator-android
UML

----------------------------------------
|               MainActivity            |
----------------------------------------
| - resultText: TextView                |
| - operation: Char                      |
| - operand1: Double                     |
| - operand2: Double                     |
| - isOperand1Input: Boolean             |
----------------------------------------
| + onCreate(savedInstanceState: Bundle)|
| + onButtonClick(view: View)            |
| + handleBackspace()                    |
| + updateResultText(value: String)      |
| + clearCalculator()                    |
| + performOperation()                   |
----------------------------------------
