package server.project.service

import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.project.auth.JwtService
import server.project.domain.User
import server.project.domain.UserRole
import server.project.dto.user.request.UserRequest
import server.project.dto.user.response.RegisterResponse
import server.project.repository.TodoRepository
import server.project.repository.UserRepository

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtService: JwtService,
    private val passwordEncoder: PasswordEncoder,
    private val authenticationManager: AuthenticationManager,
    private val todoRepository: TodoRepository
) {

    @Transactional
    fun register(request: UserRequest): RegisterResponse {
        val isExistNickname = userRepository.findByNickname(nickname = request.nickname)?.nickname
        if (isExistNickname != null) {
            throw IllegalArgumentException("Nickname already exists.")
        }
        val password = passwordEncoder.encode(request.password)
        val newUser = User(request.nickname, password, mutableSetOf(UserRole.USER))
        userRepository.save(newUser)
        val token = jwtService.generateToken(newUser)
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.nickname,
                request.password
            )
        )
        return RegisterResponse(newUser.id, newUser.nickname, token)
    }

    fun login(userRequest: UserRequest): RegisterResponse {
        val user = userRepository.findByNickname(userRequest.nickname)

        if (user != null) {
            if (!passwordEncoder.matches(userRequest.password, user.password)) {
                throw IllegalArgumentException("Invalid password.")
            }
            authenticationManager.authenticate(
                UsernamePasswordAuthenticationToken(
                    userRequest.nickname,
                    userRequest.password
                )
            )
            return RegisterResponse(id = user.id, user.nickname, token = jwtService.generateToken(user))
        } else {
            throw IllegalArgumentException("User not found.")
        }
    }

    @Transactional
    fun deleteUser(id: Long) {
        todoRepository.deleteAll()
        userRepository.deleteById(id)
    }

}