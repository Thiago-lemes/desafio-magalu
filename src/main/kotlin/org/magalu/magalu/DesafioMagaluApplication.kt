package org.magalu.magalu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class DesafioMagaluApplication

fun main(args: Array<String>) {
    runApplication<DesafioMagaluApplication>(*args)
}
