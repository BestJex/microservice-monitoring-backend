package org.microservice.monitoring.platform;

import org.hzero.autoconfigure.platform.EnableHZeroPlatform;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroPlatform
@EnableDiscoveryClient
@SpringBootApplication
public class PlatformApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlatformApplication.class, args);
    }
}


