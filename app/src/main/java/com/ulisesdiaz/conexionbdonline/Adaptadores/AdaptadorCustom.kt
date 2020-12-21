package com.ulisesdiaz.conexionbdonline.Adaptadores

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ulisesdiaz.conexionbdonline.Interfaces.ClickListener
import com.ulisesdiaz.conexionbdonline.Interfaces.LongClickListener
import com.ulisesdiaz.conexionbdonline.Models.Alumno
import com.ulisesdiaz.conexionbdonline.R

/****
 * Project: ConexionBDonline
 * From: com.ulisesdiaz.conexionbdonline.Adaptadores
 * Created by: Ulises Diaz on 20/12/20 ar 12:04 PM
 * All rights reserved 2020
 ****/
class AdaptadorCustom(items: ArrayList<Alumno>, var listener: ClickListener, var longClickListener: LongClickListener): RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {

    var items: ArrayList<Alumno>? = null
    var itemsSeleccionados: ArrayList<Int>? = null
    var viewHolder: ViewHolder? = null

    init {
        this.items = items
        this.itemsSeleccionados = ArrayList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.template_alumno, parent, false)
        viewHolder = ViewHolder(view, listener, longClickListener)
        return viewHolder!!
    }

    override fun onBindViewHolder(holder: AdaptadorCustom.ViewHolder, position: Int) {
        val item = items?.get(position)

        holder.id?.text = item?.id
        holder.nombre?.text = item?.nombre

    }

    override fun getItemCount(): Int {
       return items?.count()!!
    }

    class ViewHolder(view: View, clickListener: ClickListener, longClickListener: LongClickListener): RecyclerView.ViewHolder(view), View.OnClickListener, View.OnLongClickListener{

        val view = view;
        var id: TextView? = null
        var nombre: TextView? = null

        var clickListener: ClickListener? = null
        var longClickListener: LongClickListener? = null

        init {
            id = view.findViewById(R.id.txtId)
            nombre = view.findViewById(R.id.txtNombre)

            this.clickListener = clickListener
            this.longClickListener = longClickListener
            view.setOnClickListener(this)
            view.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.clickListener?.clickListener(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longClickListener?.longClickListener(v!!, adapterPosition)
            return true
        }

    }

}