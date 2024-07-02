package com.example.tasklist.data

import android.adservices.common.AdTechIdentifier
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import android.database.sqlite.SQLiteOpenHelper
import android.nfc.Tag
import android.util.Log

class MydatabaseHelper(val context: Context, factory: SQLiteDatabase.CursorFactory?):
SQLiteOpenHelper(context,DATABASE_NAME, factory, DATABASE_VERSION){

    companion object {

        const val DATABASE_VERSION = 1
        const val DATABASE_NAME = "tareas.db"
        const val TABLA_TAREAS = "tareas"
        const val COLUMNA_ID = "_id"
        const val COLUMNA_TAREA ="agregar_tarea"

    }

     override fun onCreate(db: SQLiteDatabase?) {
         try{
             val crearTablaTareas = "CREATE TABLE $TABLA_TAREAS" +
                     "($COLUMNA_ID INTERGER PRIMARY KEY AUTOINCREMENT, " +
                     "${COLUMNA_TAREA}_TAREA)"
             db!!.execSQL(crearTablaTareas)

         } catch (e:SQLiteException){
            Log.i("SQLiteException", "INGRESO DE DATOS")
         }
     }

     override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
         try {
             val dropTablaTareas = "DROP TABLE IF EXISTS $TABLA_TAREAS"
             db!!.execSQL(dropTablaTareas)
             onCreate(db)

         }catch (e:SQLiteException){
             Log.i("SQLiteException", "BORRADO DE DATOS")
         }
     }

     override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
         onUpgrade(db, oldVersion, newVersion)

     }



    fun agregarTarea (tarea:String) {
        val data = ContentValues()
        data.put(COLUMNA_TAREA, tarea)

        val db = this.writableDatabase
        db.insert( TABLA_TAREAS, null, data)
        db.close()

    }



    fun eliminarTarea(identifier: Int): Int {
        val args = arrayOf(identifier.toString())
        val db = this.writableDatabase
        val result = db.delete(TABLA_TAREAS,"$COLUMNA_ID =?", args)
        db.close()
        return result


    }



    fun  actualizarTarea(identifier: Int, nuevaTarea: String){
        val args = arrayOf(identifier.toString())

        val data = ContentValues()
        data.put(COLUMNA_TAREA, nuevaTarea)

        val db = this.writableDatabase
        db.update(TABLA_TAREAS, data, "$COLUMNA_ID =?",args)
        db.close()



    }

}