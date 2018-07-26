package com.example.guilhermehayashi.maonamassabasico1

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.guilhermehayashi.maonamassabasico1.network.*
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.detalhe_activity.*

class DetalheActivity: AppCompatActivity() {

    var place: ApiDetailResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detalhe_activity)
        place = Gson().fromJson(intent.extras.get("LUGAR") as String, ApiDetailResponse::class.java)
        tituloText.setText(place?.name)
        descricaoText.setText("Endereço: ${place?.address}")
        var notaTexto: String = "Nota: %.2f".format(place?.review_avg ?: 5.0)
        notaText.setText(notaTexto)

        listaReviews.adapter = ReviewsAdapter(place!!.reviews.toMutableList(), this)

        Picasso.get().load(place?.foto).into(imagem)


        updateButton.setOnClickListener {

            avaliarlayout.visibility= View.VISIBLE



        }

        cancelarbutton.setOnClickListener({
            avaliarlayout.visibility=View.GONE
        })

        okbutton.setOnClickListener({
            var comentario= comentariotext.text.toString()
            var pontuacao= numbertext.text.toString().toInt()
            var evaluation = ApiSaveReviewRequest(place=place!!.id, score=pontuacao, comment=comentario)
            Api.evaluate(evaluation, {
                finish()
            })


        })



    }
}