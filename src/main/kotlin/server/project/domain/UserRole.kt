package server.project.domain

import org.springframework.security.core.authority.SimpleGrantedAuthority

enum class UserRole {
    USER,

    ADMIN;

    fun toGrantedAuthority(): SimpleGrantedAuthority {
        return SimpleGrantedAuthority(this.name)
    }

}