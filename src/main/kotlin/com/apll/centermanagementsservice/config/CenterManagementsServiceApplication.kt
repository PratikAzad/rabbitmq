package com.apll.centermanagementsservice.config


import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Import
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.scheduling.annotation.EnableScheduling

@EnableScheduling
@EnableJpaRepositories(basePackages = arrayOf("com.apll.centermanagementsservice"))
@EntityScan(basePackages = arrayOf("com.apll.centermanagementsservice"))
@ComponentScan(basePackages = arrayOf("com.apll.centermanagementsservice"))
@Import(SwaggerConfig::class, StreamConfig::class)
@SpringBootApplication
class CenterManagementsServiceApplication

fun main(args: Array<String>) {
    runApplication<CenterManagementsServiceApplication>(*args)
}