package server.project.controller

import org.springframework.web.bind.annotation.*
import server.project.dto.user.request.UserRequest
import server.project.dto.user.response.RegisterResponse
import server.project.service.UserService

@RestController
class UserController(private val userService: UserService) {

    @PostMapping("/register")
    fun register(@RequestBody request: UserRequest): RegisterResponse {
        return userService.register(request)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: UserRequest): RegisterResponse {
        return userService.login(request)
    }

    @DeleteMapping("/user/{id}")
    fun deleteUser(@PathVariable id: Long) {
        userService.deleteUser(id)
    }

}