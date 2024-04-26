package server.project.repository

import org.springframework.data.jpa.repository.JpaRepository
import server.project.domain.User

interface UserRepository: JpaRepository<User, Long> {

    fun findByNickname(nickname: String): User?

}