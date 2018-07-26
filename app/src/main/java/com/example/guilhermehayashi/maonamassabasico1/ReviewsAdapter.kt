package com.example.guilhermehayashi.maonamassabasico1

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.guilhermehayashi.maonamassabasico1.network.ApiReviewResponse
import com.example.guilhermehayashi.maonamassabasico1.network.Pokemon

class ReviewsAdapter(var reviews: MutableList<ApiReviewResponse>, var context: Context): RecyclerView.Adapter<ReviewsAdapter.ReviewViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        return ReviewViewHolder(LayoutInflater.from(context).inflate(R.layout.review_view_holder, parent, false))
    }

    override fun getItemCount(): Int {
        return reviews.count()
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        holder.bind(reviews[position])
    }

    class ReviewViewHolder(var view: View): RecyclerView.ViewHolder(view) {
        var commentText: TextView? = null
        var scoreText: TextView? = null
        var userText: TextView? = null

        init {
            this.commentText = view.findViewById(R.id.commentText)
            this.scoreText = view.findViewById(R.id.scoreText)
            this.userText = view.findViewById(R.id.usuarioText)
        }

        fun bind(review: ApiReviewResponse) {
            this.commentText?.text = "Comentario: ${review.comment}"
            this.scoreText?.text = "Nota: ${review.score.toString()}"
            this.userText?.text = "Por usuario: ${review.user.nome}"

        }
    }

}