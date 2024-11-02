package com.example.practice9

import android.icu.text.DecimalFormat
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.practice9.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.buttonClear.setOnClickListener{
            binding.input.text = " "
            binding.output.text = " "
        }
        binding.buttonBracketLeft.setOnClickListener {
            addToInputText("(")
        }
        binding.buttonBracketRight.setOnClickListener {
            addToInputText(")")
        }
        binding.button0.setOnClickListener {
            addToInputText("0")
        }
        binding.button1.setOnClickListener {
            addToInputText("1")
        }
        binding.button2.setOnClickListener {
            addToInputText("2")
        }
        binding.button3.setOnClickListener {
            addToInputText("3")
        }
        binding.button4.setOnClickListener {
            addToInputText("4")
        }
        binding.button5.setOnClickListener {
            addToInputText("5")
        }
        binding.button6.setOnClickListener {
            addToInputText("6")
        }
        binding.button7.setOnClickListener {
            addToInputText("7")
        }
        binding.button8.setOnClickListener {
            addToInputText("8")
        }
        binding.button9.setOnClickListener {
            addToInputText("9")
        }
        binding.buttonDot.setOnClickListener {
            addToInputText(".")
        }
        binding.buttonDivision.setOnClickListener {
            addToInputText("÷")
        }
        binding.buttonMultiply.setOnClickListener {
            addToInputText("x")
        }
        binding.buttonSubtraction.setOnClickListener {
            addToInputText("-")
        }
        binding.buttonAddition.setOnClickListener {
            addToInputText("+")
        }
        binding.buttonEquals.setOnClickListener {
            showResult()
        }
        binding.buttonPercent.setOnClickListener {
            addToInputText("%")
        }
    }
    private fun addToInputText(value: String) {
        binding.input.append(value)
    }
    private fun getInputExpression(): String {
        return binding.input.text.toString()

    }
    private fun showResult() {
        val expression = getInputExpression().replace("%", "/100").replace("÷","/").replace("x","*")
        val result: String = try {
            val evaluatedResult = evaluateExpression(expression)
            formatResult(evaluatedResult)
        } catch (e: Exception) {
            handleError()
            return
        }

        displayResult(result)
    }

    private fun evaluateExpression(expression: String): Double {
        return ExpressionBuilder(expression).build().evaluate()
    }
    
    private fun formatResult(result: Double): String {
        return DecimalFormat("0.######").format(result).toString()
    }

    private fun handleError() {
        binding.output.apply {
            text = "Ошибка"
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.red))
        }
    }

    private fun displayResult(result: String) {
        binding.output.apply {
            text = result
            setTextColor(ContextCompat.getColor(this@MainActivity, R.color.neon_green))
        }
    }
}