package server.project.service

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import server.project.domain.Todo
import server.project.domain.User
import server.project.dto.user.request.TodoCreateRequest
import server.project.repository.TodoRepository
import server.project.repository.UserRepository

@SpringBootTest
class TodoServiceTest @Autowired constructor(
    private val todoService: TodoService,
    private val todoRepository: TodoRepository,
    private val userRepository: UserRepository,
) {

    @AfterEach
    fun cleanup() {
        todoRepository.deleteAll()
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("Get todo.")
    fun getTodoById() {
        val user = userRepository.save(User("test", "test"))
        val todo = todoRepository.save(Todo(user, "test"))
        val result = todoService.getTodoById(todo.id)
        assertThat(result.text).isEqualTo(todo.text)
        assertThat(result.id).isEqualTo(todo.id)
        assertThat(result.user.id).isEqualTo(user.id)
    }

    @Test
    @DisplayName("Failed to get todo: Todo not found.")
    fun getTodoNotFound() {
        val exception = assertThrows(IllegalArgumentException::class.java) {
            todoService.getTodoById(1L)
        }
        assertEquals("Todo not found.", exception.message)
    }

    @Test
    @DisplayName("Todo save success.")
    fun saveTodo() {
        val user = userRepository.save(User("test", "test"))
        val request = TodoCreateRequest(user.id, "memo")
        todoService.saveTodo(request)
        val result = todoRepository.findAll()
        assertThat(result).hasSize(1)
        assertThat((result[0].text)).isEqualTo(request.text)
        assertThat((result[0].user.id)).isEqualTo(user.id)
    }

    @Test
    @DisplayName("Failed to save todo: User not found.")
    fun saveTodoForUserNotFound() {
        val request = TodoCreateRequest(1L, "memo")
        val exception = assertThrows(IllegalArgumentException::class.java) {
            todoService.saveTodo(request)
        }
        assertEquals("User not found.", exception.message)
    }

}