package com.exampletasklist.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tasklist.data.Tarea
import com.example.tasklist.data.TareaDB
import com.example.tasklist.databinding.ActivityTareaBinding

class TareaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTareaBinding

    private lateinit var tareaDB: TareaDB
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =ActivityTareaBinding.inflate(layoutInflater)
        setContentView(binding.root)


        tareaDB = TareaDB(this)

        binding.saveButton.setOnClickListener {
            val taskName = binding.nameEditText.text.toString()
            val task = Tarea(-1, taskName)
            tareaDB.insert(task)
            Toast.makeText(this, "Tarea guardad correctamente", Toast.LENGTH_SHORT).show()
            finish()
        }
    }



}