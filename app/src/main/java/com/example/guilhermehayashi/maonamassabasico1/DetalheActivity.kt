package com.example.guilhermehayashi.maonamassabasico1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.guilhermehayashi.maonamassabasico1.network.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.detalhe_activity.*
import java.text.SimpleDateFormat
import java.util.*

class DetalheActivity: AppCompatActivity() {

    var pet: ApiDetailResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhe_activity)
        pet = Gson().fromJson(intent.extras.get("PET") as String, ApiDetailResponse::class.java)
        Api.getTasks(SimpleDateFormat("YYYY-MM-dd").format(Date())){
            it.results.forEach({
                nomeEditText.setText(it.question)
                descricaoEditText.setText(it.answer)
            })
        }
        updateButton.setOnClickListener {
            var nome = nomeEditText.text.toString()
            var descricao = descricaoEditText.text.toString()
            var petRequest = ApiSaveRequest(dono=Api.user!!.id.toString(), nome=nome, descricao=descricao)
            Api.update(pet!!.id.toString(), petRequest, {
                finish()
            })
        }
    }
}