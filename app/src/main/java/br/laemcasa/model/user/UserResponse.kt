package br.laemcasa.model.user

import java.util.UUID

data class UserResponse(
    val id: UUID,
    val email: String,
    val username: String,
    val password: String
)
