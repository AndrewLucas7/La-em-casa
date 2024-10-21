package br.laemcasa.model.user

data class UserRequest(
    val email: String,
    val username: String,
    val password: String
)
