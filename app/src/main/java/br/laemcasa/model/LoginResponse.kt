package br.laemcasa.model

data class LoginResponse(
    val token: String,
    val success: Boolean,
    val message: String
)
