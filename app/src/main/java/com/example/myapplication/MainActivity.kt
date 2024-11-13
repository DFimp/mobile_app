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
import java.io.FileInputStream
import java.io.FileOutputStream
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private val fileName = "data.txt"
    private val historyFileName = "history.txt"

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
        val textViewHistory = findViewById<TextView>(R.id.textViewHistory)
        val buttonCalculate = findViewById<Button>(R.id.buttonCalculate)
        val buttonReset = findViewById<ImageButton>(R.id.buttonReset)
        val buttonClearHistory = findViewById<Button>(R.id.buttonClearHistory)

        // Загрузка данных и истории при запуске
        loadData(editTextArea, editTextServicePrice, checkBoxHeating, checkBoxWater)
        loadHistory(textViewHistory)

        // Обработка нажатия на кнопку "Рассчитать"
        buttonCalculate.setOnClickListener {
            val area = editTextArea.text.toString().toDoubleOrNull() ?: 0.0
            val servicePrice = editTextServicePrice.text.toString().toDoubleOrNull() ?: 0.0

            var totalCost = area * servicePrice

            if (checkBoxHeating.isChecked) {
                totalCost += 500
            }

            if (checkBoxWater.isChecked) {
                totalCost += 300
            }

            val resultText = "Стоимость: $totalCost руб."
            textViewResult.text = resultText

            // Сохранение данных и добавление записи в историю
            saveData(editTextArea, editTextServicePrice, checkBoxHeating, checkBoxWater)
            appendHistory(resultText)
            loadHistory(textViewHistory)
        }

        // Обработка нажатия на кнопку "Сброс"
        buttonReset.setOnClickListener {
            editTextArea.text.clear()
            editTextServicePrice.text.clear()
            checkBoxHeating.isChecked = false
            checkBoxWater.isChecked = false
            textViewResult.text = "Стоимость: 0 руб."
            deleteFile(fileName)
        }

        // Обработка нажатия на кнопку "Очистить историю"
        buttonClearHistory.setOnClickListener {
            deleteFile(historyFileName)
            textViewHistory.text = "История расчетов:"
        }
    }

    // Метод для сохранения данных
    private fun saveData(
        editTextArea: EditText,
        editTextServicePrice: EditText,
        checkBoxHeating: CheckBox,
        checkBoxWater: CheckBox
    ) {
        val data = "${editTextArea.text};${editTextServicePrice.text};${checkBoxHeating.isChecked};${checkBoxWater.isChecked}"
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput(fileName, MODE_PRIVATE)
            fos.write(data.toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fos?.close()
        }
    }

    // Метод для загрузки данных
    private fun loadData(
        editTextArea: EditText,
        editTextServicePrice: EditText,
        checkBoxHeating: CheckBox,
        checkBoxWater: CheckBox
    ) {
        var fis: FileInputStream? = null
        try {
            fis = openFileInput(fileName)
            val bytes = ByteArray(fis.available())
            fis.read(bytes)
            val savedData = String(bytes).split(";")
            if (savedData.size == 4) {
                editTextArea.setText(savedData[0])
                editTextServicePrice.setText(savedData[1])
                checkBoxHeating.isChecked = savedData[2].toBoolean()
                checkBoxWater.isChecked = savedData[3].toBoolean()
            }
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fis?.close()
        }
    }

    // Метод для добавления записи в историю
    private fun appendHistory(record: String) {
        var fos: FileOutputStream? = null
        try {
            fos = openFileOutput(historyFileName, MODE_APPEND)
            fos.write((record + "\n").toByteArray())
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fos?.close()
        }
    }

    // Метод для загрузки истории
    private fun loadHistory(textViewHistory: TextView) {
        var fis: FileInputStream? = null
        try {
            fis = openFileInput(historyFileName)
            val bytes = ByteArray(fis.available())
            fis.read(bytes)
            textViewHistory.text = "История расчетов:\n" + String(bytes)
        } catch (e: IOException) {
            textViewHistory.text = "История расчетов:"
            e.printStackTrace()
        } finally {
            fis?.close()
        }
    }
}
