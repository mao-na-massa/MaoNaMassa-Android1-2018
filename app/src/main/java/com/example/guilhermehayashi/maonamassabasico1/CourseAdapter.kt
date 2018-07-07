package com.example.guilhermehayashi.maonamassabasico1

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.guilhermehayashi.maonamassabasico1.network.Api
import com.example.guilhermehayashi.maonamassabasico1.network.ApiDetailResponse
import com.google.gson.Gson

class CourseAdapter(var courses: MutableList<ApiDetailResponse>, var context: Context): RecyclerView.Adapter<CourseAdapter.CourseViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        return CourseViewHolder(LayoutInflater.from(context).inflate(R.layout.course_view_holder, parent, false), context)
    }

    override fun getItemCount(): Int {
        return courses.count()
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        holder.configurar(courses[position])
    }

    class CourseViewHolder(var view: View, var context: Context): RecyclerView.ViewHolder(view) {

        var nomeTextView: TextView? = null
        var joinButton: Button? = null
        var leaveButton: Button? = null
        var course: ApiDetailResponse? = null
        var resposta: Button? = null
        var respostaEditText: EditText? = null

        init {
            nomeTextView = view.findViewById(R.id.nomeTextView)
            resposta = view.findViewById(R.id.botaoResposta)
            respostaEditText = view.findViewById(R.id.respostaEditText)
            resposta?.setOnClickListener({
                if(respostaEditText!!.text.toString() == course!!.answer) {
                    resposta?.setBackgroundColor(context.resources.getColor(R.color.green))
                } else {
                    resposta?.setBackgroundColor(context.resources.getColor(R.color.colorAccent))
                }
            })

        }

        fun configurar(course: ApiDetailResponse) {
            this.course = course
            nomeTextView?.text = course.question

        }


    }

}

