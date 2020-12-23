package com.home.work.placegoat.receyler

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.home.work.placegoat.R
import com.home.work.placegoat.Result
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class RecyclerAdapter(private val items: List<Result>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.recyclerview_items, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

}

class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val name = itemView.findViewById<TextView>(R.id.tv_name)
    private val status = itemView.findViewById<TextView>(R.id.tv_status)
    private val species = itemView.findViewById<TextView>(R.id.tv_species)
    private val image = itemView.findViewById<ImageView>(R.id.iv_character)
    private val context = view.context

    fun bind(item: Result) {

        name.text = item.name
        status.text = item.status
        species.text = item.species

        try {
            Picasso.with(context)
                .load(item.image)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(image)
        }catch (e:Exception){
            Toast.makeText(context,"Ошибка $e",Toast.LENGTH_LONG).show()
        }
    }
}