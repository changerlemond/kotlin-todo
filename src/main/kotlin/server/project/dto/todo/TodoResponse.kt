package server.project.dto.todo

import server.project.domain.Todo

class TodoResponse(todo: Todo) {
    val id = todo.id
    val userId = todo.user.id
    val userNickName = todo.user.nickname
    val text = todo.text
    val status = todo.status
    val createDateTime = todo.createdDateTime
    val updateDateTime = todo.updatedDateTime
}