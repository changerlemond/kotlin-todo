package server.project.controller

import org.springframework.web.bind.annotation.*
import server.project.dto.user.request.UserRequest
import server.project.dto.user.response.UserResponse
import server.project.service.UserService

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/user")
    fun signUp(@RequestBody request: UserRequest): UserResponse {
        return userService.saveUser(request)
    }

    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

}