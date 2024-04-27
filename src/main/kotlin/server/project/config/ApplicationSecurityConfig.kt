package server.project.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import server.project.repository.UserRepository

@Configuration
class ApplicationSecurityConfig(
    private val userRepository: UserRepository
) {
    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()


    @Bean
    fun userDetailsService() = UserDetailsService { userRepository.findByNickname(it) }


    @Bean
    fun authenticationManager(configuration: AuthenticationConfiguration): AuthenticationManager =
        configuration.authenticationManager


    @Bean
    fun authenticationProvider() = DaoAuthenticationProvider().apply {
        setUserDetailsService(userDetailsService())
        setPasswordEncoder(passwordEncoder())
    }

}