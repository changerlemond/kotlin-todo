package server.project.dto.user.request

import jakarta.validation.constraints.NotNull

data class TodoCreateRequest(
    @field:NotNull
    val userId: Long,

    @field:NotNull
    val text: String,
)