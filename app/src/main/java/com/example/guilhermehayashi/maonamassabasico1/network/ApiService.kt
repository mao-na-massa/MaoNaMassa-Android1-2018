package com.example.guilhermehayashi.maonamassabasico1.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.GET
import rx.Observable

/**
 * Created by João Vitor da Nova on 16/06/2018.
 */
public interface ApiService {

    @GET("pets")
    fun getPets(): Observable<ApiPetsResponse>

}

class ApiPetsResponse(
        @SerializedName(value = "count")
        @Expose var count: Int,

        @SerializedName(value = "results")
        @Expose var results: List<Pet>
)

class Pet (
        @SerializedName(value = "nome")
        @Expose var nome: String,

        @SerializedName(value = "tipo")
        @Expose var tipo: String,

        @SerializedName(value = "descricao")
        @Expose var descricao: String,

        @SerializedName(value = "localizacao")
        @Expose var localizacao: String
)