package org.sva.hello.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class HelloController {

    @GetMapping("/hello")
    fun sayHello(): Mono<String> {
        return  Mono.just("hello kot!")
    }
}