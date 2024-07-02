package com.example.tasklist

import android.os.Bundle
import android.text.Layout
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

import com.example.tasklist.data.MydatabaseHelper
import com.example.tasklist.databinding.ActivityMainBinding
import org.intellij.lang.annotations.Identifier

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var tareasDbHelper: MydatabaseHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        tareasDbHelper = MydatabaseHelper(this,null)
        with(binding){

            btnAgregar.setOnClickListener {
                if (editText.text.isNotBlank()){
                    tareasDbHelper.agregarTarea(editText.text.toString()
                    )

                    editText.clearComposingText()
                }
                else{ Toast.makeText(this, "Tarea agregada", Toast.LENGTH_SHORT).show() }

            }


            btnActualizar.setOnClickListener {
                if(editText.text.isNotBlank()){
                    solicitarIdentificador(UPDATE)
                } else{ Toast.makeText(this, "Tarea agregada", Toast.LENGTH_SHORT).show()
                    }


            }





            fun solicitarIdentificador(accion: String){
                val myDialogView = LayoutInflater.from(this@MainActivity).inflate(R.layout.dialogo,null)
            }


        }







    }



    }
