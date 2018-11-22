package com.ayala.manuel.kotlin_omdb_api


import android.content.Context
import android.content.Intent
import android.support.design.widget.Snackbar
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_datos.view.*
import android.support.v4.content.ContextCompat.startActivity




class DatosAdapter(val items : ArrayList<Pelicula>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    // Obtiene el número de datos
    override fun getItemCount(): Int {
        return items.size
    }

    //infla el layout activity_datos
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.fragment_datos, parent, false))
    }

    // carga datos del ArrayList aL TEXTVIEW view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvDatosA?.text = items.get(position).Title
        holder?.tvDatosB?.text = items.get(position).Year
        holder?.tvDatosC?.text = "Imdb id: " + items.get(position).imdbID
        holder?.ivDatos?.loadUrl(items.get(position).Poster)
/*        holder?.ivDatos?.setOnClickListener(View.OnClickListener {
            Snackbar.make(holder?.view, items.get(position).imdbID, Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        })*/

        holder?.ivDatos?.setOnClickListener(View.OnClickListener {
            val intent = Intent(context.getApplicationContext(),ActivityPelicula::class.java)
            intent.putExtra("imdb", items.get(position).imdbID)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_MULTIPLE_TASK
            Snackbar.make(holder?.view, "Cargando", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
            context.applicationContext.startActivity(intent)
        })
    }
    fun ImageView.loadUrl(url: String) {
        var imagen = url
        if (imagen.equals("N/A"))
            imagen = "http://iesayala.ddns.net/jaidis/not-found.jpg"
        else
            imagen = imagen.replace("http://", "https://")
        Picasso.with(context).load(imagen).into(this)
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Mantiene el TextView
    val tvDatosA = view.textViewTitulo
    val tvDatosB = view.textViewAnno
    val tvDatosC = view.textViewId
    val ivDatos= view.ivDato
    val view = view



}
