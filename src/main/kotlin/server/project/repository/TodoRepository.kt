package server.project.repository

import org.springframework.data.jpa.repository.JpaRepository
import server.project.domain.Todo

interface TodoRepository: JpaRepository<Todo, Long> {
}