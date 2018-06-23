package com.example.guilhermehayashi.maonamassabasico1

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.guilhermehayashi.maonamassabasico1.network.*
import kotlinx.android.synthetic.main.login_activity.*
import java.text.SimpleDateFormat
import java.util.*

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        var dia = SimpleDateFormat("dd").format(Date())
        var mes = SimpleDateFormat("MMMM").format(Date())
        var ano = SimpleDateFormat("yyyy").format(Date())

        dataAtualTextView.setText(dia+" de "+mes+" de "+ ano)



        loginButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val senha = senhaEditText.text.toString()
            Api.login(email, senha, {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            })
        }
    }

}