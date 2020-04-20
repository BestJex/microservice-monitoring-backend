package org.microservice.monitoring.scheduler;

import org.hzero.autoconfigure.scheduler.EnableHZeroScheduler;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroScheduler
@EnableDiscoveryClient
@SpringBootApplication
public class SchedulerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SchedulerApplication.class, args);
    }
}


