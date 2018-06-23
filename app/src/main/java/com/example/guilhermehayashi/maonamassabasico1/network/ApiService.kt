package com.example.guilhermehayashi.maonamassabasico1.network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import retrofit2.http.*
import rx.Observable

interface ApiService {

    @GET("api/${Api.path}/")
    fun list(): Observable<ApiListResponse>

    @GET("api/${Api.path}/{id}/")
    fun get(@Path("id") id: String): Observable<ApiDetailResponse>

    @POST("api/${Api.path}/")
    fun save(@Body request: ApiSaveRequest): Observable<ApiDetailResponse>

    @POST("api/${Api.path}/{id}/join/")
    fun join(@Path("id") id: String): Observable<Unit>

    @POST("api/${Api.path}/{id}/leave/")
    fun leave(@Path("id") id: String): Observable<Unit>

    @GET("api/${Api.tasksPath}/")
    fun getTasks(@Query("date") date: String): Observable<ApiListResponse>

    @PATCH("api/${Api.path}/{id}/")
    fun update(@Path("id") id: String, @Body request: ApiSaveRequest): Observable<ApiDetailResponse>

    @POST("auth/api/v1/login")
    fun login(@Body loginRequest: LoginRequest): Observable<LoginResponse>

}

open class LoginRequest(
        @SerializedName("email")
        @Expose var email: String,

        @SerializedName("password")
        @Expose var password: String
)

open class LoginResponse(
        @SerializedName("token")
        @Expose var token: String,

        @SerializedName("user")
        @Expose var user: User
)

open class User(
        @SerializedName("id")
        @Expose var id: Int,

        @SerializedName("nome")
        @Expose var nome: String,

        @SerializedName("email")
        @Expose var email: String
)

open class ApiListResponse(
        @SerializedName("count")
        @Expose var count: Int,

        @SerializedName("results")
        @Expose var results: List<ApiDetailResponse>
)

open class ApiSaveRequest(

        @SerializedName("dono")
        @Expose var dono: String,

        @SerializedName("nome")
        @Expose var nome: String,

        @SerializedName("tipo")
        @Expose var tipo: String? = null,

        @SerializedName("descricao")
        @Expose var descricao: String

)

open class ApiDetailResponse(

        @SerializedName("name")
        @Expose var nome: String,

        @SerializedName("question")
        @Expose var question: String,

        @SerializedName("answer")
        @Expose var answer: String,

        @SerializedName("id")
        @Expose var id: Int

)