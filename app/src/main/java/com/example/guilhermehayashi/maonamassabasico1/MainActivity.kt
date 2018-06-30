package com.example.guilhermehayashi.maonamassabasico1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import com.example.guilhermehayashi.maonamassabasico1.modelos.Comida
import com.example.guilhermehayashi.maonamassabasico1.modelos.Pessoa
import com.example.guilhermehayashi.maonamassabasico1.network.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.main_layout.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    var cursos: MutableList<ApiDetailResponse> = mutableListOf()
    var cursosNomes: MutableList<String> = mutableListOf()

    object companion {
        val nameKey: String = "NOME_DO_USUARIO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)


 /*       novoPetButton.setOnClickListener {
            val intent = Intent(this, NovoPetActivity::class.java)
            startActivity(intent)
        }*/



        entrarButton.setOnClickListener({
            var curso = cursos.get(cursosSpinner.selectedItemPosition)
            Api.join(curso.id.toString(), {
                Log.d("JOIN", "JOINED COURSE")
            })

        })

        sairButton.setOnClickListener({
            var curso = cursos.get(cursosSpinner.selectedItemPosition)
            Api.leave(curso.id.toString(), {
                Log.d("LEAVE", "LEFT COURSE")
            })
        })

    }

    override fun onResume() {
        super.onResume()
        Api.list {
            cursos = it.results.toMutableList()
            cursosNomes.removeAll {true}
            cursos.forEach( {
                cursosNomes.add(it.nome)
            })
            cursosSpinner.adapter = ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, cursosNomes)
        }
    }

}
