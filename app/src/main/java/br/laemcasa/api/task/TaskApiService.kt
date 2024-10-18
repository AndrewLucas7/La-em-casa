package br.laemcasa.api.task

import br.laemcasa.model.Task
import retrofit2.Call
import retrofit2.http.*

interface TaskApiService {
    @GET("/tasks")
    fun getTasks(): Call<List<Task>>

    @GET("/tasks/{id}")
    fun getTaskById(@Path("id") id: Int): Call<Task>

    @POST("/tasks")
    fun createTask(@Body task: Task): Call<Task>

    @PUT("/tasks/{id}")
    fun updateTask(@Path("id") id: Int, @Body task: Task): Call<Task>

    @DELETE("/tasks/{id}")
    fun deleteTask(@Path("id") id: Int): Call<Void>
}