package br.laemcasa.model.user

import java.util.UUID

data class UserUpdateResponse(
    val id: UUID,
    val username: String,
    val password: String,
)
