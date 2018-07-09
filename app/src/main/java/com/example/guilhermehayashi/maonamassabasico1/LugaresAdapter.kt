package com.example.guilhermehayashi.maonamassabasico1

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.guilhermehayashi.maonamassabasico1.network.ApiDetailResponse
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class LugaresAdapter(var pets: MutableList<ApiDetailResponse>, var context: Context): RecyclerView.Adapter<LugaresAdapter.LugarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LugarViewHolder {
        return LugarViewHolder(LayoutInflater.from(context).inflate(R.layout.pet_view_holder, parent, false), context)
    }

    override fun getItemCount(): Int {
        return pets.count()
    }

    override fun onBindViewHolder(holder: LugarViewHolder, position: Int) {
        holder.configurar(pets[position])
    }

    class LugarViewHolder(var view: View, var context: Context): RecyclerView.ViewHolder(view) {

        var image:ImageView? = null
        var nomeTextView: TextView? = null
        var lugar: ApiDetailResponse? = null

        init {
            image = view.findViewById(R.id.LugarPhoto)
            nomeTextView = view.findViewById(R.id.Lugartext)
            view.setOnClickListener {
                var intent = Intent(context, DetalheActivity::class.java)
                intent.putExtra("LUGAR", Gson().toJson(lugar))
                context.startActivity(intent)
            }
        }

        fun configurar(pet: ApiDetailResponse) {
            this.lugar = pet
            nomeTextView?.text = pet.name
            Picasso.get().load(pet.foto).into(image)

        }

    }

}

