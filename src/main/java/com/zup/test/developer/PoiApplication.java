package com.zup.test.developer;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.hazelcast.HazelcastAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
//@EnableAutoConfiguration(exclude = {HazelcastAutoConfiguration.class})
@EntityScan(basePackages = { "com.zup.test.developer.service", "com.zup.test.developer.data.domain", "com.zup.test.developer.service.impl"})
@EnableJpaRepositories(basePackages = { "com.zup.test.developer.data.repository"})
@ComponentScan(basePackages = {"com.zup.test.developer.controller", "com.zup.test.developer.data.domain", "com.zup.test.developer.service.impl"})
public class PoiApplication {
    public static void main(String[] args) {
        SpringApplication.run(PoiApplication.class, args);
    }
}
