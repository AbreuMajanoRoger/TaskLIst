package com.example.tasklist.data

import android.content.ContentValues
import android.content.Context
import android.provider.BaseColumns
import com.example.tasklist.utils.MydatabaseHelper

class TareaDB (context:Context) {

    private val databaseManager: MydatabaseHelper = MydatabaseHelper(context)

    fun insert(tarea: Tarea) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Tarea.COLUMN_NAME_TITLE, tarea.name)
        values.put(Tarea.COLUMN_NAME_DONE, tarea.done)

        val newRowId = db.insert(Tarea.TABLE_NAME, null, values)
        tarea.id = newRowId.toInt()

        db.close()
    }

    fun update(tarea: Tarea) {
        val db = databaseManager.writableDatabase

        val values = ContentValues()
        values.put(Tarea.COLUMN_NAME_TITLE, tarea.name)
        values.put(Tarea.COLUMN_NAME_DONE, tarea.done)

        val updatedRows = db.update(
            Tarea.TABLE_NAME,
            values,
            "${BaseColumns._ID} = ${tarea.id}",
            null
        )

        db.close()
    }

    fun delete(tarea: Tarea) {
        val db = databaseManager.writableDatabase

        val deletedRows = db.delete(Tarea.TABLE_NAME, "${BaseColumns._ID} = ${tarea.id}", null)

        db.close()
    }

    fun find(id: Int): Tarea? {
        val db = databaseManager.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Tarea.COLUMN_NAME_TITLE, Tarea.COLUMN_NAME_DONE)

        val cursor = db.query(
            Tarea.TABLE_NAME,                        // The table to query
            projection,                             // The array of columns to return (pass null to get all)
            "${BaseColumns._ID} = $id",      // The columns for the WHERE clause
            null,                         // The values for the WHERE clause
            null,                            // don't group the rows
            null,                             // don't filter by row groups
            null                             // The sort order
        )

        var tarea: Tarea? = null
        if (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(Tarea.COLUMN_NAME_TITLE))
            val done = cursor.getInt(cursor.getColumnIndexOrThrow(Tarea.COLUMN_NAME_DONE)) == 1
            tarea = Tarea(id, name, done)
        }
        cursor.close()
        db.close()
        return tarea
    }

    fun findAll(): List<Tarea> {
        val db = databaseManager.readableDatabase

        val projection = arrayOf(BaseColumns._ID, Tarea.COLUMN_NAME_TITLE, Tarea.COLUMN_NAME_DONE)

        val cursor = db.query(
            Tarea.TABLE_NAME,                        // The table to query
            projection,                             // The array of columns to return (pass null to get all)
            null,                            // The columns for the WHERE clause
            null,                         // The values for the WHERE clause
            null,                            // don't group the rows
            null,                             // don't filter by row groups
            "${Tarea.COLUMN_NAME_DONE} ASC"                             // The sort order
        )

        var tarea = mutableListOf<Tarea>()
        while (cursor.moveToNext()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(BaseColumns._ID))
            val name = cursor.getString(cursor.getColumnIndexOrThrow(Tarea.COLUMN_NAME_TITLE))
            val done = cursor.getInt(cursor.getColumnIndexOrThrow(Tarea.COLUMN_NAME_DONE)) == 1
            val task = Tarea(id, name, done)
            tarea.add(task)
        }
        cursor.close()
        db.close()
        return tarea
    }


}