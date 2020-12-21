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

class DetalleAlumnoActivity : AppCompatActivity() {

    var crud: AlumnoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_alumno)
        val editId = findViewById<EditText>(R.id.editId)
        val editNombre = findViewById<EditText>(R.id.editNombre)
        val btnActualizar = findViewById<Button>(R.id.btnActualizarAlumno)
        val btnEliminar = findViewById<Button>(R.id.btnElminarAlumno)

        val idAlumno = intent.getStringExtra("ID")

        crud = AlumnoCRUD(this)

        val item = crud?.getAlumno(idAlumno!!)

        editId.setText(item?.id)
        editNombre.setText(item?.nombre)

        val context = this.applicationContext
        var network = Network(this)
        val gson = Gson()

        btnActualizar.setOnClickListener {
        /*    val alumno = Alumno(editId.text.toString(), editNombre.text.toString())
            crud?.updateAlumno(Alumno(editId.text.toString(), editNombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))*/

            val query = "?id=${URLEncoder.encode(editId.text.toString(), "UTF-8")}&nombre=${URLEncoder.encode(editNombre.text.toString(), "UTF-8")}"
            val url = "https://sqliteonline.000webhostapp.com/actualizaralumno${query}"

            network.httpRequest(context, url, object: HttpResponse{
                override fun httpResponseSuccess(response: String) {
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

        btnEliminar.setOnClickListener {
    /*        crud?.deleteAlumno(Alumno(editId.text.toString(), editNombre.text.toString()))
            startActivity(Intent(this, MainActivity::class.java))*/
            val query = "?id=${URLEncoder.encode(editId.text.toString(), "UTF-8")}&nombre=${URLEncoder.encode(editNombre.text.toString(), "UTF-8")}"
            val url = "https://sqliteonline.000webhostapp.com/eliminaralumno${query}"

            network.httpRequest(context, url, object: HttpResponse{
                override fun httpResponseSuccess(response: String) {
                    val message = gson.fromJson(response, HttpApiResponse::class.java)
                    Toast.makeText(context, message.response, Toast.LENGTH_SHORT).show()
                    startActivity(Intent(context, MainActivity::class.java))
                    crud?.deleteAlumno(Alumno(editId.text.toString(), editNombre.text.toString()))

                }

                override fun httpErrorResponse(response: String) {
                    Log.d("Error response", response)
                    Toast.makeText(context, "Hubo un problema al enviar la solicitud", Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}