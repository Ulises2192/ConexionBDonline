package com.ulisesdiaz.conexionbdonline.DB

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

/****
 * Project: ConexionBDonline
 * From: com.ulisesdiaz.conexionbdonline.DB
 * Created by: Ulises Diaz on 20/12/20 ar 01:24 PM
 * All rights reserved 2020
 ****/
class DataBaseHelper(context: Context): SQLiteOpenHelper(
    context, AlumnosContract.DATABASE_NAME, null, AlumnosContract.VERSION){

    companion object{
        val CREATE_TABLE_ALUMNOS =
            "CREATE TABLE ${AlumnosContract.Companion.Entrada.NOMBRE_TABLA} (" +
                    "${AlumnosContract.Companion.Entrada.COLUMNA_ID} TEXT PRIMARY KEY, " +
                    "${AlumnosContract.Companion.Entrada.COLUMNA_NOMBRE} TEXT)"

        val REMOVE_TABLE_ALUMNOS = "DROP TABLE IF EXISTS ${AlumnosContract.Companion.Entrada.NOMBRE_TABLA}"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(CREATE_TABLE_ALUMNOS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(REMOVE_TABLE_ALUMNOS)
        onCreate(db)
    }
}