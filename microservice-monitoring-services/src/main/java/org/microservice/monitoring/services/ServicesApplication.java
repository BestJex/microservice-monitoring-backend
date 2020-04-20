package org.microservice.monitoring.services;

import io.choerodon.resource.annoation.EnableChoerodonResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
@EnableChoerodonResourceServer
public class ServicesApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServicesApplication.class, args);
    }

    public ServicesApplication() {
        System.setProperty("es.set.netty.runtime.available.processors","false");
    }

}
