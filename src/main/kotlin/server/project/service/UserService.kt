package server.project.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.project.domain.User
import server.project.dto.user.request.UserCreateRequest
import server.project.repository.UserRepository

@Service
class UserService(private val userRepository: UserRepository) {

    @Transactional
    fun saveUser(request: UserCreateRequest): User {
        val newUser = User(request.nickname, request.password)
        return userRepository.save(newUser)
    }

}