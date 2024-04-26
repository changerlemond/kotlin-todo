package server.project.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.project.domain.User
import server.project.dto.user.request.UserCreateRequest
import server.project.dto.user.response.UserResponse
import server.project.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun saveUser(request: UserCreateRequest): UserResponse {
        val isExistNickname = userRepository.findByNickname(nickname = request.nickname)?.nickname
        if (isExistNickname != null) {
            throw IllegalArgumentException("Nickname already exists")
        }

        val newUser = User(request.nickname, request.password)
        userRepository.save(newUser)

        return UserResponse.user(newUser)
    }

    @Transactional
    fun deleteUser(id: Long) {
        userRepository.deleteById(id)
    }

}