package com.ulisesdiaz.conexionbdonline.Utils

import android.content.Context
import android.widget.Toast

/****
 * Project: ConexionBDonline
 * From: com.ulisesdiaz.conexionbdonline.Utils
 * Created by: Ulises Diaz on 20/12/20 ar 11:59 AM
 * All rights reserved 2020
 ****/
class Mensaje {
    companion object{
        fun mensajeSuccess(){

        }

        fun  mensaje(context: Context, mensaje: Mensajes){
            var str = ""
            when (mensaje){
                Mensajes.RATIONALE ->{
                    str = "Requiero permisos para obtener ubiciacion"
                }

                Mensajes.CHECKIN_SUCCESS ->{
                    str = "Nuevo Checkin añadido"
                }

                Mensajes.LIKE_SUCCESS ->{
                    str = "Nuevo Like añadido"
                }
            }
            Toast.makeText(context, str, Toast.LENGTH_SHORT).show()
        }

        fun mensajeError(context:Context, error: Errores){
            var mensaje = ""
            when(error){
                Errores.NO_HAY_RED ->{
                    mensaje = "No hay una conexion disponible"
                }

                Errores.HTTP_ERROR ->{
                    mensaje = "Hubo un problema en la solicitud"
                }

                Errores.NO_HAY_APP_FSQR ->{
                    mensaje = "No tienes instalada la app de foursquare"
                }

                Errores.ERROR_CONEXION_FSQR ->{
                    mensaje = "No se pudo completar la conexion a Foursquare"
                }

                Errores.ERROR_INTERCAMBIO_TOKEN ->{
                    mensaje = "No se pudo completar el intercambio de Token en foursquare"
                }

                Errores.ERROR_GUARDAR_TOKEN ->{
                    mensaje = "No se pudo guardar el token"
                }

                Errores.PERMISO_NEGADO ->{
                    mensaje = "No se otorgaron los permisos para tu ubicacion"
                }

                Errores.ERROR_QUERY ->{
                    mensaje = "Hubo un problema en la solicitud a la API"
                }
            }
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        }

        fun mensajeError(context:Context, error: String){
            Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
        }

    }
}