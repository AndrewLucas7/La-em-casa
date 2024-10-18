package br.laemcasa.api.user

import br.laemcasa.model.User
import retrofit2.Call
import retrofit2.http.*

interface UserApiService {
    @GET("/users")
    fun getUsers(): Call<List<User>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: Int): Call<User>

    @POST("/users")
    fun createUser(@Body user: User): Call<User>

    @PUT("/users/{id}")
    fun updateUser(@Path("id") id: Int, @Body user: User): Call<User>

    @DELETE("/users/{id}")
    fun deleteUser(@Path("id") id: Int): Call<Void>
}