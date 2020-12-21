package com.ulisesdiaz.conexionbdonline.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.ulisesdiaz.conexionbdonline.Models.Alumno

/****
 * Project: ConexionBDonline
 * From: com.ulisesdiaz.conexionbdonline.DB
 * Created by: Ulises Diaz on 20/12/20 ar 01:58 PM
 * All rights reserved 2020
 ****/
class AlumnoCRUD (context: Context) {

    private var helper: DataBaseHelper? = null

    init {
        helper = DataBaseHelper(context)
    }

    fun newAlumno(item: Alumno){

        // Abrir la base de datos en modo escritura
        val db: SQLiteDatabase = helper?.writableDatabase!!

        // Mapeo de las columnas con los valores a insertar
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item.nombre)

        // insertar una nueva fila a la tabla
        val newRowId = db.insert(AlumnosContract.Companion.Entrada.NOMBRE_TABLA, null, values)

        // Cerrar la base de datos
        db.close()

    }

    /**
     * Obtiene todos los elementos de la tabla Alumnos
     */
    fun getAlumnos(): ArrayList<Alumno>{
        val items:ArrayList<Alumno> = ArrayList()

        // Abrir la base de datos en modo lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        // Especifiacar columnas que se va a consultar
        val columnas = arrayOf(
            AlumnosContract.Companion.Entrada.COLUMNA_ID,
            AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE
        )

        // Cursor para recorrer la tabla
        val cursor: Cursor = db.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            null,
            null,
            null,
            null,
            null,
            null
        )

        // Realiza el recorrido del cursor en la tabla
        while(cursor.moveToNext()){
            items.add(Alumno(
                cursor.getString(cursor.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE))
            ))
        }

        // Cerrar la Base de datos
        db.close()
        return  items
    }

    /**
     * Obtiene elementos de la tabla Alumno por Id
     */
    fun getAlumno(id: String): Alumno{
        var item: Alumno? = null

        // Abrir la base de datos en modo lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        // Especifiar columna a consultar
        val columnas = arrayOf(
            AlumnosContract.Companion.Entrada.COLUMNA_ID,
            AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE
        )

        // Cursor para recorrer la tabla
        val cursor: Cursor = db.query(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            columnas,
            " id=?",
            arrayOf(id),
            null,
            null,
            null
        )

        // Realiza el recorrido del cursor en la tabla
        while(cursor.moveToNext()){
            item = Alumno(cursor.getString(cursor.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_ID)),
                cursor.getString(cursor.getColumnIndexOrThrow(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE)))
        }

        // Cerrar la Base de datos
        db.close()

        return item!!
    }

    /**
     * Actualizar un elemento de la Tabla alumno
     */
    fun updateAlumno(item: Alumno){

        // Abrir la base de datos en modo lectura
        val db: SQLiteDatabase = helper?.readableDatabase!!

        // Mapeo de las columnas con los valores a actualizar
        val values = ContentValues()
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_ID, item?.id)
        values.put(AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE, item?.nombre)

        // Actualiza el elemento por medi de su id
        db.update(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            values,
            "id=?",
            arrayOf(item?.id)
        )

        // Cerrar la Base de datos
        db.close()
    }

    /**
     * Elimmina un registro de la Tabla Alumo por id
     */
    fun deleteAlumno(item: Alumno){
        val db: SQLiteDatabase = helper?.writableDatabase!!

        // Elimina un registro de la base de datos acorde a un Id
        db.delete(
            AlumnosContract.Companion.Entrada.NOMBRE_TABLA,
            "id=?",
            arrayOf(item.id))

        // Cerrar la Base de datos
        db.close()
    }
}