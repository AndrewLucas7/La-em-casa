package br.laemcasa.api.user

import br.laemcasa.model.user.UserCreateRequest
import br.laemcasa.model.user.UserCreateResponse
import br.laemcasa.model.user.UserResponse
import br.laemcasa.model.user.UserUpdateRequest
import br.laemcasa.model.user.UserUpdateResponse
import retrofit2.Call
import retrofit2.http.*
import java.util.UUID

interface UserApiService {
    @GET("/users")
    fun getUsers(): Call<List<UserResponse>>

    @GET("/users/{id}")
    fun getUserById(@Path("id") id: UUID): Call<UserResponse>

    @POST("/users")
    fun createUser(@Body user: UserCreateRequest): Call<UserCreateResponse>

    @PUT("/users/{id}")
    fun updateUser(@Path("id") id: UUID, @Body user: UserUpdateRequest): Call<UserUpdateResponse>

    @PATCH("/users/{id}")
    fun updateField(@Path("id") id: UUID, @Body user: UserUpdateRequest): Call<UserUpdateResponse>

    @DELETE("/users/{id}")
    fun deleteUser(@Path("id") id: UUID): Call<Void>
}