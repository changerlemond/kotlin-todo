package server.project.service

import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import server.project.dto.user.request.UserRequest
import server.project.repository.UserRepository
import org.assertj.core.api.AssertionsForInterfaceTypes.assertThat
import org.junit.jupiter.api.*

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userService: UserService,
    private val userRepository: UserRepository
) {

    @AfterEach
    fun cleanup() {
        userRepository.deleteAll()
    }

    @Test
    @DisplayName("User save success.")
    fun saveUser() {
        val request = UserRequest("test", "test")
        userService.saveUser(request)
        val result = userRepository.findAll()
        assertThat(result).hasSize(1)
        assertThat(result[0].nickname).isEqualTo(request.nickname)
    }

    @Test
    @DisplayName("Failed to save user: Duplicate nickname found.")
    fun saveUserWithDuplicateNickname() {
        val request = UserRequest("test", "test")
        userService.saveUser(request)
        val exception = assertThrows(IllegalArgumentException::class.java) { userService.saveUser(request) }
        assertThat(exception).hasMessageContaining("Nickname already exists.")
    }

    @Test
    @DisplayName("User delete success.")
    fun deleteUser() {
        val request = UserRequest("test", "test")
        val user = userService.saveUser(request)
        userService.deleteUser(user.id)
        val userExists = userRepository.existsById(user.id)
        assertThat(userExists).isFalse()
    }

}