package com.example.mtsfinalprojecteurekaserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class MtsFinalProjectEurekaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(MtsFinalProjectEurekaServerApplication.class, args);
    }

}
