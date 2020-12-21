package com.ulisesdiaz.conexionbdonline.Interfaces

/****
 * Project: ConexionBDonline
 * From: com.ulisesdiaz.conexionbdonline.Interfaces
 * Created by: Ulises Diaz on 20/12/20 ar 11:57 AM
 * All rights reserved 2020
 ****/
interface HttpResponse {

    fun httpResponseSuccess(response: String)

    fun httpErrorResponse(response: String)
}