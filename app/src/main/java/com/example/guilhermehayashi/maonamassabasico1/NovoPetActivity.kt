package com.example.guilhermehayashi.maonamassabasico1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.guilhermehayashi.maonamassabasico1.network.*
import kotlinx.android.synthetic.main.novo_curso_activity.*


class NovoPetActivity: AppCompatActivity() {

    //val tipos = arrayListOf("cachorro", "gato", "outro")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.novo_curso_activity)
        //tipoSpinner.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tipos)
        criarButton.setOnClickListener {
            val nome = nomePetEditText.text.toString()
           // val descricao = descricaoEditText.text.toString()
            // val tipo = tipos[tipoSpinner.selectedItemPosition]
            val dono = Api.user?.id
            val pet = ApiSaveRequest(dono!!.toString(), nome)
            Api.save(pet, {
                finish()
            })
        }
    }

}