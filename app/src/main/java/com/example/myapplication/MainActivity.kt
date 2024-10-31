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

        val eng = Engineer(101, "Jane Smith", "012-34-5678", 120_345.27)
        val mgr = Manager(207, "Barbara Johnson", "054-12-2367", 109_501.36, "US Marketing")
        val adm = Admin(304, "Bill Munroe", "108-23-2367", 75_002.34)
        val dir = Director(12, "Susan Wheeler", "099-45-2340", 120_567.36, "Global Marketing", 1_000_000.00)

        printEmployee(eng)
        printEmployee(mgr)
        printEmployee(adm)
        printEmployee(dir)
    }

    private fun printEmployee(emp: Employee) {
        Log.d("EmployeeTest", "Employee ID: ${emp.empId}")
        Log.d("EmployeeTest", "Employee Name: ${emp.name}")
        Log.d("EmployeeTest", "Employee Soc Sec #: ${emp.ssn}")
        Log.d("EmployeeTest", "Employee salary: ${emp.salary}")
    }
}

