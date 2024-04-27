package server.project.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.project.domain.User
import server.project.domain.UserRole
import server.project.dto.user.request.UserRequest
import server.project.dto.user.response.UserResponse
import server.project.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun saveUser(request: UserRequest): UserResponse {
        val isExistNickname = userRepository.findByNickname(nickname = request.nickname)?.nickname
        if (isExistNickname != null) {
            throw IllegalArgumentException("Nickname already exists.")
        }
        val newUser = User(request.nickname, request.password, mutableSetOf(UserRole.USER))
        return UserResponse.user(userRepository.save(newUser))
    }

    @Transactional
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

}