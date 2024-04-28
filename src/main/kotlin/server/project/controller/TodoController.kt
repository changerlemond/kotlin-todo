package server.project.controller

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import server.project.domain.User
import server.project.dto.todo.TodoResponse
import server.project.dto.user.request.TodoCreateRequest
import server.project.service.TodoService

@RestController
class TodoController(private val todoService: TodoService) {

    @GetMapping("/")
    fun home(): String {
        return "Hello World!"
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/todo/{id}")
    fun getByTodoById(@PathVariable id: Long): TodoResponse {
        return todoService.getTodoById(id)
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/todo/list")
    fun getTodosByUser(
        @AuthenticationPrincipal user: User,
        @PageableDefault(page = 0, size = 10) pageable: Pageable
    ): Page<TodoResponse> {
        return todoService.getTodosByUser(user.id, pageable)
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/todo/latest")
    fun getByTodoByLatest(@AuthenticationPrincipal user: User): TodoResponse? {
        return todoService.getLatestTodoByUser(user.id)
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/todo/create")
    fun saveTodo(@RequestBody request: TodoCreateRequest): TodoResponse {
        return todoService.saveTodo(request)
    }

}