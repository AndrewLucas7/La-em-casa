package br.laemcasa.model.user

data class UserCreateRequest(
    val email: String,
    val username: String,
    val password: String
)
