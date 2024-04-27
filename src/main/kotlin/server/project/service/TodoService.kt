package server.project.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.project.domain.Todo
import server.project.dto.user.request.TodoCreateRequest
import server.project.repository.TodoRepository
import server.project.repository.UserRepository

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun getTodoById(id: Long): Todo {
        val todo = todoRepository.findById(id)
        if (!todo.isPresent) {
            throw IllegalArgumentException("Todo not found.")
        }
        return todo.get()
    }

    @Transactional
    fun getTodosByUser(userId: Long, pageable: Pageable): Page<Todo> {
        return todoRepository.findByUserId(userId, pageable)
    }

    @Transactional
    fun getLatestTodoByUser(userId: Long): Todo? {
        return todoRepository.findLatestByUserId(userId)
    }

    @Transactional
    fun saveTodo(request: TodoCreateRequest): Todo {
        val user = userRepository.findById(request.userId)
        if (!user.isPresent) {
            throw IllegalArgumentException("User not found.")
        }
        val newTodo = Todo(user.get(), request.text)
        return todoRepository.save(newTodo)
    }

}