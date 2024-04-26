package server.project.dto.user.request

import jakarta.validation.constraints.NotNull

data class UserCreateRequest(
    @field:NotNull
    val nickname: String,
    @field:NotNull
    val password: String
)