package com.example.guilhermehayashi.maonamassabasico1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.guilhermehayashi.maonamassabasico1.network.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detalhe_activity.*

class DetalheActivity: AppCompatActivity() {

    var pet: ApiDetailResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhe_activity)
        pet = Gson().fromJson(intent.extras.get("PET") as String, ApiDetailResponse::class.java)
        var imageUrl: String? = null
        pet?.images?.firstOrNull()?.image?.let { image ->
            imageUrl = "http://mnm-miaudote.herokuapp.com" + image
        }


        Picasso.get()
                .load(imageUrl)
                .placeholder(R.drawable.pet_back)
                .into(imageView);

            nomeEditText.setText(pet?.nome)
            descricaoEditText.setText(pet?.descricao)


    /*   updateButton.setOnClickListener {
            var nome = nomeEditText.text.toString()
            var descricao = descricaoEditText.text.toString()
            var petRequest = ApiSaveRequest(dono=Api.user!!.id.toString(), nome=nome, descricao=descricao)
            Api.update(pet!!.id.toString(), petRequest, {
                finish()
            })
        }*/
    }
}