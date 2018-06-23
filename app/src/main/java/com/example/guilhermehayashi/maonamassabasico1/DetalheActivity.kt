package com.example.guilhermehayashi.maonamassabasico1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.guilhermehayashi.maonamassabasico1.network.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.detalhe_activity.*

class DetalheActivity: AppCompatActivity() {

    var place: ApiDetailResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhe_activity)
        place = Gson().fromJson(intent.extras.get("PET") as String, ApiDetailResponse::class.java)
        nomeEditText.setText(place?.name)

        updateButton.setOnClickListener {
            var descricao = descricaoEditText.text.toString()
            var evaluation = ApiSaveReviewRequest(place=place!!.id, score=5, comment=descricao)
            Api.evaluate(evaluation, {
                finish()
            })
        }
    }
}