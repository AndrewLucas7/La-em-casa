package br.laemcasa.api.user

import br.laemcasa.model.user.UserRequest
import br.laemcasa.model.user.UserResponse
import br.laemcasa.model.user.UserUpdate
import retrofit2.Call
import retrofit2.http.*
import java.util.UUID

interface UserApiService {
    @GET("/users")
    fun getUsers(): Call<List<UserResponse>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: UUID): Call<UserResponse>

    @POST("/users")
    fun createUser(@Body user: UserRequest): Call<UserRequest>

    @PUT("/users/{id}")
    fun updateUser(@Path("id") id: UUID, @Body user: UserUpdate): Call<UserUpdate>

    @DELETE("/users/{id}")
    fun deleteUser(@Path("id") id: UUID): Call<Void>
}