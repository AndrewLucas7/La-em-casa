package br.laemcasa.model.login

data class LoginResponse(
    val token: String,
    val success: Boolean,
    val message: String
)
