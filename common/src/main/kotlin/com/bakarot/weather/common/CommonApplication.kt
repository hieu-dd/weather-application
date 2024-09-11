package com.bakarot.weather.common

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class CommonApplication{
	@Bean
	fun commandLineRunner() = CommandLineRunner {
		println("Hello, World!")
	}
}

fun main(args: Array<String>) {
	runApplication<CommonApplication>(*args)
}
