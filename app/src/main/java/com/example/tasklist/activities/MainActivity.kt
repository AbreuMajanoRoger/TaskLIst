package com.example.tasklist.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasklist.adapters.TareaAdapter
import com.example.tasklist.data.Tarea
import com.example.tasklist.data.TareaDB

import com.example.tasklist.utils.MydatabaseHelper
import com.example.tasklist.databinding.ActivityMainBinding
import com.exampletasklist.activities.TareaActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: TareaAdapter
    private lateinit var tareaList: List<Tarea>
    private lateinit var tareaDB: TareaDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tareaDB = TareaDB(this)

        adapter = TareaAdapter = TareaAdapter(emptyList(), {
            Toast.makeText(this, "Click en tarea: ${tareaList[it].name}", Toast.LENGTH_SHORT).show()
        }, {
            tareaDB.delete(tareaList[it])
            Toast.makeText(this, "Tarea borrada correctamente", Toast.LENGTH_SHORT).show()
            loadData()
        }, {
            val tarea = tareaList[it]
            tarea.done = !tarea.done
            tareaDB.update(tarea)
            loadData()
        })

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)


        binding.addTaskButton.setOnClickListener {
            val intent = Intent(this, TareaActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadData()
    }

    private fun loadData() {
        tareaList = tareaDB.findAll()

        adapter.updateData(tareaList)
        }

    }
