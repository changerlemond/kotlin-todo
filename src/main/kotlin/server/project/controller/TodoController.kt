package server.project.controller

import org.springframework.web.bind.annotation.*
import server.project.domain.Todo
import server.project.dto.user.request.TodoCreateRequest
import server.project.service.TodoService

@RestController
class TodoController(private val todoService: TodoService) {

    // TODO: 테스트를 위한 컨트롤러, 추후 제거
    @GetMapping("/")
    fun home(): String {
        return "Hello World!"
    }

    @GetMapping("/todo/{id}")
    fun getByTodoById(@PathVariable id: Long): Todo {
        return todoService.getTodoById(id)
    }

    @PostMapping("/todo")
    fun saveTodo(@RequestBody request: TodoCreateRequest): Todo {
        return todoService.saveTodo(request)
    }

}