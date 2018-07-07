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
import kotlinx.android.synthetic.main.activity_lista_tarefas.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.login_activity.*
import kotlinx.android.synthetic.main.main_layout.*
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    var cursos: MutableList<ApiDetailResponse> = mutableListOf()
    var cursosNomes: MutableList<String> = mutableListOf()
    var tarefas: MutableList<ApiDetailResponse> = mutableListOf()
    var date = Calendar.getInstance()

    object companion {
        val nameKey: String = "NOME_DO_USUARIO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)

        var dia = SimpleDateFormat("dd").format(date.time)
        Log.d("Data", dia.toString())



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

        verTarefas.setOnClickListener({
            val intent = Intent(this, ListaTarefas::class.java)
            startActivity(intent)


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
