package server.project.auth

import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import server.project.repository.UserRepository

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByNickname(username)
        ?: throw UsernameNotFoundException("User not found with username: $username")

        return User(user.username, user.password, user.roles.map { it.toGrantedAuthority() })
    }

}