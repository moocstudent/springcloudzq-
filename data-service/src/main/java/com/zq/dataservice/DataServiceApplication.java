package com.zq.dataservice;

import com.zq.dataservice.utils.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataServiceApplication {

    public static void main(String[] args) {
        int port = PortUtil.setPort();
        new SpringApplicationBuilder(DataServiceApplication.class).properties("server.port="+port).run(args);
//        SpringApplication.run(DataServiceApplication.class, args);
    }

}
