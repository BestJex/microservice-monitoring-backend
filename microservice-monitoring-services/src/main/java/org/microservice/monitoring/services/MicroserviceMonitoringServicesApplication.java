package org.microservice.monitoring.services;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import io.choerodon.resource.annoation.EnableChoerodonResourceServer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@EnableChoerodonResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class MicroserviceMonitoringServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(MicroserviceMonitoringServicesApplication.class, args);
    }

    public MicroserviceMonitoringServicesApplication() {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
    }

    @Bean(name="remoteRestTemplate")
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}


