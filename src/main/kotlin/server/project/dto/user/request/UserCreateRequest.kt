package server.project.dto.user.request

data class UserCreateRequest(
    val nickname: String,
    val password: String
)