package server.project.dto.todo.request

import jakarta.validation.constraints.NotNull
import server.project.domain.TodoStatus

class UpdateTodoRequest(
    @field:NotNull
    val id: Long,
    @field:NotNull
    val status: TodoStatus
)