package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Инициализация элементов интерфейса
        val editTextArea = findViewById<EditText>(R.id.editTextArea)
        val editTextServicePrice = findViewById<EditText>(R.id.editTextServicePrice)
        val checkBoxHeating = findViewById<CheckBox>(R.id.checkBoxHeating)
        val checkBoxWater = findViewById<CheckBox>(R.id.checkBoxWater)
        val textViewResult = findViewById<TextView>(R.id.textViewResult)

        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val buttonReset = findViewById<ImageButton>(R.id.buttonReset)

        // Обработка нажатия на кнопку "Рассчитать"
        buttonCalculate.setOnClickListener {
            val area = editTextArea.text.toString().toDoubleOrNull() ?: 0.0
            val servicePrice = editTextServicePrice.text.toString().toDoubleOrNull() ?: 0.0

            var totalCost = area * servicePrice

            if (checkBoxHeating.isChecked) {
                totalCost += 500  // Пример стоимости отопления
            }

            if (checkBoxWater.isChecked) {
                totalCost += 300  // Пример стоимости водоснабжения
            }

            textViewResult.text = "Стоимость: $totalCost руб."
        }

        // Обработка нажатия на кнопку "Сброс"
        buttonReset.setOnClickListener {
            editTextArea.text.clear()
            editTextServicePrice.text.clear()
            checkBoxHeating.isChecked = false
            checkBoxWater.isChecked = false
            textViewResult.text = "Стоимость: 0 руб."
        }
    }
}
