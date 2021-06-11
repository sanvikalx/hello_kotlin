package org.sva.hello.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.sva.hello.entity.Profile
import java.time.LocalDateTime

@SpringBootTest
@AutoConfigureWebTestClient
class ProfileControllerTest {

    @Autowired
    lateinit var client: WebTestClient

    @Autowired
    lateinit var applicationContext: ApplicationContext

    @Test
    fun whenRequestProfile_thenStatusShouldBeOk() {
        val profile = Profile(null, "Ivan", "Ivanov", LocalDateTime.parse("2001-06-09T19:46:32.804"));

        client.post()
            .uri("/profile")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(profile)
            .exchange().expectBody().json("""
                {
                  "id": 1,
                  "firstName": "Ivan",
                  "lastName": "Ivanov",
                  "birthDate": "2001-06-09T19:46:32.804"
                }
            """.trimIndent())
    }

}