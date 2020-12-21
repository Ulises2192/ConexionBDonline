package com.ulisesdiaz.conexionbdonline

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.ulisesdiaz.conexionbdonline.Adaptadores.AdaptadorCustom
import com.ulisesdiaz.conexionbdonline.DB.AlumnoCRUD
import com.ulisesdiaz.conexionbdonline.Interfaces.ClickListener
import com.ulisesdiaz.conexionbdonline.Interfaces.HttpResponse
import com.ulisesdiaz.conexionbdonline.Interfaces.LongClickListener
import com.ulisesdiaz.conexionbdonline.Models.Alumno
import com.ulisesdiaz.conexionbdonline.Models.Alumnos
import com.ulisesdiaz.conexionbdonline.Utils.Network

class MainActivity : AppCompatActivity() {

    var lista: RecyclerView? = null
    var adaptador: AdaptadorCustom? = null
    var layoutManager: RecyclerView.LayoutManager? = null

    var alumnos: ArrayList<Alumno>? = null
    var crud: AlumnoCRUD? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fab = findViewById<FloatingActionButton>(R.id.fab)
        lista = findViewById(R.id.lista)

        // Configuracion LayoutManager
        lista?.setHasFixedSize(true)
        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        fab.setOnClickListener {
            startActivity(Intent(this, NuevoAlumnoActivity::class.java))
        }

        val network = Network(this)
        val activity = this.applicationContext
        val gson = Gson()

        crud = AlumnoCRUD(this)
        alumnos = crud?.getAlumnos()

        network.httpRequest(activity, "https://sqliteonline.000webhostapp.com/", object: HttpResponse{
            override fun httpResponseSuccess(response: String) {
                Log.d("RESPONSE___", response)
                val alumnosApi = gson.fromJson(response, Alumnos::class.java).items

                if (!alumnosApi.isNullOrEmpty()){
                    for (alumno in alumnosApi!!){
                        crud?.deleteAlumno(alumno)
                    }
                    for (alumno in alumnosApi!!){
                        crud?.newAlumno(Alumno(alumno.id!!, alumno.nombre!!))
                    }
                }
                alumnos = crud?.getAlumnos()
                configurarAdaptador(alumnos!!)
            }

            override fun httpErrorResponse(response: String) {
                Toast.makeText(activity, "Error al hacer la solicitud HTTP", Toast.LENGTH_SHORT).show()
                configurarAdaptador(alumnos!!)
            }
        })

    }

    private fun configurarAdaptador(data: ArrayList<Alumno>){
        this.adaptador = AdaptadorCustom(data!!, object: ClickListener {
            override fun clickListener(view: View, index: Int) {
                Log.d("click", "implementar clicklistener")
                val intent = Intent(applicationContext, DetalleAlumnoActivity::class.java)
                intent.putExtra("ID", data!!.get(index).id)
                startActivity(intent)

            }

        }, object: LongClickListener {
            override fun longClickListener(view: View, index: Int) {
                Log.d("LonClick", "implementar LonListener")
            }

        })

        this.lista?.adapter = adaptador
    }

}