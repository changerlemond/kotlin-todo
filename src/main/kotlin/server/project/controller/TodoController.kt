package server.project.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TodoController {

    // TODO: 테스트를 위한 컨트롤러, 추후 제거
    @GetMapping("/")
    fun home(): String {
        return "Hello World!"
    }

}