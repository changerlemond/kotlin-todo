package server.project.controller

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import server.project.domain.User
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

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping("/user/leave")
    fun deleteUser(@AuthenticationPrincipal user: User) {
        userService.deleteUser(user.id)
    }

}