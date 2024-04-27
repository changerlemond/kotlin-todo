package server.project.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import server.project.domain.Todo

interface TodoRepositoryCustom {

    fun findByUserId(userId: Long, pageable: Pageable): Page<Todo>

    fun findLatestByUserId(userId: Long): Todo?

}