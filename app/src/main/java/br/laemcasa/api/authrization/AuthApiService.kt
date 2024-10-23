package br.laemcasa.api.authrization

import br.laemcasa.model.login.LoginRequest
import br.laemcasa.model.login.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {
    @POST("/auth/login")
    fun login(@Body login: LoginRequest): Call<LoginResponse>
}