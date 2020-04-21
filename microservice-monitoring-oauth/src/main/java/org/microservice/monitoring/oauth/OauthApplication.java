package org.microservice.monitoring.oauth;

import org.hzero.autoconfigure.oauth.EnableHZeroOauth;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableHZeroOauth
@EnableDiscoveryClient
@SpringBootApplication
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication.run(OauthApplication.class, args);
    }
}


