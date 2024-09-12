package com.bakarot.weather.service

import IP2Location
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import java.nio.file.Paths

@SpringBootApplication
@EnableJpaRepositories("com.bakarot.weather.*")
@ComponentScan(basePackages = ["com.bakarot.weather.*"])
@EntityScan("com.bakarot.weather.*")
class ServiceApplication{
    @Bean
    fun init() = CommandLineRunner {
        val projectRootPath = System.getProperty("user.dir")
        val dbPath = Paths.get(projectRootPath, "service/ip2locationdb/IP2LOCATION-LITE-DB3.IPV6.BIN").toString()
        val ipLocator = IP2Location()
        ipLocator.open(dbPath)
        val ipResult = ipLocator.ipQuery("108.30.178.78")
        println("ServiceApplication is ipResult: ${ipResult}")
    }
}

fun main(args: Array<String>) {
    runApplication<ServiceApplication>(*args)
}
