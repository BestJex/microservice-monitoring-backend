package org.microservice.monitoring.register;

import org.hzero.autoconfigure.register.EnableHZeroRegister;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.cloud.netflix.eureka.server.EurekaServerAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroRegister
@EnableEurekaServer
@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@Import(EurekaServerAutoConfiguration.class)
public class RegisterApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterApplication.class, args);
    }
}


