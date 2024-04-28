package server.project.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SwaggerConfig {
    @Bean
    fun openAPI(): OpenAPI {
        val info: Info = Info()
            .version("v1.0.0")
            .title("TODO API")
            .description("TODO API List - v1")

        return OpenAPI()
            .info(info)
    }
}