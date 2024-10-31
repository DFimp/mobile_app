package com.example.myapplication
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.domain.Admin
import com.example.myapplication.domain.Director
import com.example.myapplication.domain.Engineer
import com.example.myapplication.domain.Manager
import com.example.myapplication.domain.Employee

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Создание объектов
        val eng = Engineer(101, "Jane Smith", "012-34-5678", 120_345.27)
        val mgr = Manager(207, "Barbara Johnson", "054-12-2367", 109_501.36, "US Marketing")
        val adm = Admin(304, "Bill Munroe", "108-23-2367", 75_002.34)
        val dir = Director(12, "Susan Wheeler", "099-45-2340", 120_567.36, "Global Marketing", 1_000_000.00)

        // Вывод информации о сотрудниках
        printEmployee(eng)
        printEmployee(mgr)
        printEmployee(adm)
        printEmployee(dir)
    }

    // Метод для отображения данных о сотруднике
    private fun printEmployee(emp: Employee) {
        Log.d("EmployeeTest", "ID сотрудника: ${emp.empId}")
        Log.d("EmployeeTest", "Имя сотрудника: ${emp.name}")
        Log.d("EmployeeTest", "Социальный номер: ${emp.ssn}")
        Log.d("EmployeeTest", "Зарплата сотрудника: ${emp.salary}")
    }
}

