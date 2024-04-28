package server.project.dto.exception

import server.project.exception.ExceptionCode

data class ExceptionDto(
    val code: ExceptionCode,
    val message: String?
)
