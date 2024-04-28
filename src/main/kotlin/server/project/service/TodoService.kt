package server.project.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import server.project.domain.Todo
import server.project.dto.todo.TodoResponse
import server.project.dto.user.request.TodoCreateRequest
import server.project.repository.TodoRepository
import server.project.repository.UserRepository

@Service
class TodoService(
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository
) {

    @Transactional
    fun getTodoById(id: Long): TodoResponse {
        val todo = todoRepository.findById(id)
        if (!todo.isPresent) {
            throw IllegalArgumentException("Todo not found.")
        }
        return TodoResponse(todo.get())
    }

    @Transactional
    fun getTodosByUser(userId: Long, pageable: Pageable): Page<TodoResponse> {
        val todos = todoRepository.findByUserId(userId, pageable)
        return todos.map { TodoResponse(it) }
    }

    @Transactional
    fun getLatestTodoByUser(userId: Long): TodoResponse? {
        val todo = todoRepository.findLatestByUserId(userId)
        return if (todo != null) TodoResponse(todo) else null
    }

    @Transactional
    fun saveTodo(request: TodoCreateRequest): TodoResponse {
        val user = userRepository.findById(request.userId)
        if (!user.isPresent) {
            throw IllegalArgumentException("User not found.")
        }
        val newTodo = Todo(user.get(), request.text)
        return TodoResponse(todoRepository.save(newTodo))
    }

}