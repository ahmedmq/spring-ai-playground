package com.ahmedmq.playground.app

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PlaygroundAppApplication

fun main(args: Array<String>) {
    runApplication<PlaygroundAppApplication>(*args)
}
