package com.example.guilhermehayashi.maonamassabasico1

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.guilhermehayashi.maonamassabasico1.network.*
import kotlinx.android.synthetic.main.main_layout.*

class MainActivity : AppCompatActivity() {

    object companion {
        val nameKey: String = "NOME_DO_USUARIO"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_layout)


    }

    override fun onResume() {
        super.onResume()
        Api.list {
            ListadeLugares.adapter = LugaresAdapter(it.results.toMutableList(), this)
        }
    }

}
