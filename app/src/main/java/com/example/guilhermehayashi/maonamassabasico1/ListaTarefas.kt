package com.example.guilhermehayashi.maonamassabasico1

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.example.guilhermehayashi.maonamassabasico1.network.Api
//import com.maonamassa.ckl.rotina_do_sucesso.R

import kotlinx.android.synthetic.main.activity_lista_tarefas.*
import kotlinx.android.synthetic.main.course_view_holder.*
import java.text.SimpleDateFormat
import java.util.*


class ListaTarefas : AppCompatActivity() {

    var date = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_tarefas)

        carregarTarefas()

        var dia = SimpleDateFormat("dd").format(date.time)
        diaTarefas.setText(dia.toString())

    botaoAvancarDia.setOnClickListener({
        date.add(Calendar.DATE, 1)
        var dia = SimpleDateFormat("dd").format(date.time)
        diaTarefas.setText(dia)
        carregarTarefas()
    })

    botaoVoltarDia.setOnClickListener({
        date.add(Calendar.DATE, -1)
        var dia = SimpleDateFormat("dd").format(date.time)
        diaTarefas.setText(dia)
        carregarTarefas()
    })

    }

    fun carregarTarefas() {
        var dia = SimpleDateFormat("yyyy-MM-dd").format(date.time)
        Api.getTasks(dia, {

            listaTarefas.adapter = CourseAdapter(it.results.toMutableList(), this)


        })
    }
}
