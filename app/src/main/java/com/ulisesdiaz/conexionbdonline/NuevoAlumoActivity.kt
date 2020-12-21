package com.ulisesdiaz.conexionbdonline

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.gson.Gson
import com.ulisesdiaz.conexionbdonline.DB.AlumnoCRUD
import com.ulisesdiaz.conexionbdonline.Interfaces.HttpResponse
import com.ulisesdiaz.conexionbdonline.Models.Alumno
import com.ulisesdiaz.conexionbdonline.Models.HttpApiResponse
import com.ulisesdiaz.conexionbdonline.Utils.Network
import java.net.URLEncoder

class NuevoAlumnoActivity : AppCompatActivity() {

    var crud:  AlumnoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_alumno)

        val id = findViewById<EditText>(R.id.editId)
        val nombre = findViewById<EditText>(R.id.editNombre)
        val btnNuevoAlumno = findViewById<Button>(R.id.btnNuevoAlumno)

        crud = AlumnoCRUD(this)

        btnNuevoAlumno.setOnClickListener {
           /* crud?.newAlumno(Alumno(id.text.toString(), nombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))*/

            val context = this.applicationContext
            var network = Network(this)

            val query = "?id=${URLEncoder.encode(id.text.toString(), "UTF-8")}&nombre=${URLEncoder.encode(nombre.text.toString(), "UTF-8")}"
            val url = "https://sqliteonline.000webhostapp.com/nuevoalumno${query}"

            network.httpRequest(context, url, object: HttpResponse{
                override fun httpResponseSuccess(response: String) {
                    val gson = Gson()
                    val message = gson.fromJson(response, HttpApiResponse::class.java)
                    Toast.makeText(context, message.response, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context, MainActivity::class.java))
                }

                override fun httpErrorResponse(response: String) {
                    Log.d("Error response", response)
                    Toast.makeText(context, "Hubo un problema al enviar la solicitud", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}