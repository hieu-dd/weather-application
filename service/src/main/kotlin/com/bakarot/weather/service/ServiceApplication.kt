package com.bakarot.weather.service

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class ServiceApplication{
	@Bean
	fun commandLineRunner() = CommandLineRunner {
		println("Hello, World!")
	}
}

fun main(args: Array<String>) {
	runApplication<ServiceApplication>(*args)
}
