package server.project.dto.user.response

import server.project.domain.User

data class UserResponse(
    val id: Long,
    val nickname: String,
) {
    companion object {
        fun user(user: User): UserResponse {
            return UserResponse(
                id = user.id,
                nickname = user.nickname,
            )
        }
    }
}