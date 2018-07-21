package com.example.guilhermehayashi.maonamassabasico1.network

import android.util.Log
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

object Api {

    var url = "https://mnm-rotina.herokuapp.com/"
    const val path = "courses"
    const val tasksPath = "user-tasks"

    var userToken: String = ""
    var user: User? = null

    fun login(email: String, password: String, callback: (response: LoginResponse) -> Unit, callbackError: (error: Throwable) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofit(false)
        retrofit?.create(ApiService::class.java)
                ?.login(LoginRequest(email, password))
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    Api.user = it.user
                    Api.userToken = it.token
                    callback(it)
                }, {
                    callbackError(it)
                })
    }

    fun list(callback: (response: ApiListResponse) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofit(true)
        retrofit?.create(ApiService::class.java)
                ?.list()
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    callback(it)
                }, {
                    Log.d("API", "Erro: ${it}")
                })
    }

    fun save(body: ApiSaveRequest, callback: (response: ApiDetailResponse) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofit(true)
        retrofit?.create(ApiService::class.java)?.save(body)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    callback(it)
                }, {
                    Log.d("API", "Erro: ${it}")
                })
    }

    fun update(id: String, body: ApiSaveRequest, callback: (response: ApiDetailResponse) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofit(true)
        retrofit?.create(ApiService::class.java)?.update(id, body)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    callback(it)
                }, {
                    Log.d("API", "Erro: ${it}")
                })
    }

    fun get(id: String, callback: (response: ApiDetailResponse) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofit(true)
        retrofit?.create(ApiService::class.java)?.get(id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    callback(it)
                }, {
                    Log.d("API", "Erro: ${it}")
                })
    }

    fun getTasks(date: String, callback: (response: ApiListResponse) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofit(true)
        retrofit?.create(ApiService::class.java)?.getTasks(date)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    callback(it)
                }, {
                    Log.d("API", "Erro: ${it}")
                })
    }

    fun join(id: String, callback: (response: Unit) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofit(true)
        retrofit?.create(ApiService::class.java)?.join(id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    callback(it)
                }, {
                    Log.d("API", "Erro: ${it}")
                })
    }

    fun leave(id: String, callback: (response: Unit) -> Unit) {
        val retrofit = RetrofitHelper.getRetrofit(true)
        retrofit?.create(ApiService::class.java)?.leave(id)
                ?.subscribeOn(Schedulers.io())
                ?.observeOn(AndroidSchedulers.mainThread())
                ?.subscribe({
                    callback(it)
                }, {
                    Log.d("API", "Erro: ${it}")
                })
    }

}