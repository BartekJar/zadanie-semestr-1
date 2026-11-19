package com.technischools.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
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

        val washNumberInput = findViewById<EditText>(R.id.washNumberInput)
        val washNumberStatus = findViewById<TextView>(R.id.washNumberStatus)
        val confirmWashButton = findViewById<Button>(R.id.confirmWashButton)

        confirmWashButton.setOnClickListener {
            val washNumber = washNumberInput.text.toString().toIntOrNull()
            if (washNumber == null || washNumber !in 1..12) {
                washNumberInput.error = getString(R.string.wash_number_error)
                washNumberInput.requestFocus()
            } else {
                washNumberInput.error = null
                washNumberStatus.text = getString(R.string.wash_number_selected, washNumber)
            }
        }

        val vacuumToggleButton = findViewById<Button>(R.id.vacuumToggleButton)
        val vacuumStatus = findViewById<TextView>(R.id.vacuumStatus)
        var vacuumIsOn = false

        vacuumToggleButton.setOnClickListener {
            vacuumIsOn = !vacuumIsOn
            if (vacuumIsOn) {
                vacuumToggleButton.setText(R.string.turn_off)
                vacuumStatus.setText(R.string.vacuum_on)
            } else {
                vacuumToggleButton.setText(R.string.turn_on)
                vacuumStatus.setText(R.string.vacuum_off)
            }
        }
    }
}
