package org.microservice.monitoring.swagger;

import org.hzero.autoconfigure.swagger.EnableHZeroSwagger;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroSwagger
@EnableDiscoveryClient
@SpringBootApplication
public class SwaggerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SwaggerApplication.class, args);
    }
}


