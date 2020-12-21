package com.ulisesdiaz.conexionbdonline.DB

import android.provider.BaseColumns

/****
 * Project: ConexionBDonline
 * From: com.ulisesdiaz.conexionbdonline.DB
 * Created by: Ulises Diaz on 20/12/20 ar 01:22 PM
 * All rights reserved 2020
 ****/
class AlumnosContract {

    companion object{
        val VERSION =1
        val DATABASE_NAME = "alumno"

        class Entrada: BaseColumns {
            companion object{
                val NOMBRE_TABLA = "alumnos"
                val COLUMNA_ID = "id"
                val COLUMNA_NOMBRE = "nombre"
            }
        }
    }

}