package br.laemcasa.model.user

import java.sql.Timestamp
import java.util.UUID

data class UserCreateResponse(
    val id: UUID,
    val email: String,
    val username: String,
    val password: String,
    val role: UserRole,
    val createdAt: Timestamp
)
