package com.zq.zuulservice;

import com.zq.utilboot.utils.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
@EnableDiscoveryClient
public class ZuulServiceApplication {

    public static void main(String[] args) {
        PortUtil.checkPort();
        int port = PortUtil.setPort(args, 2, "默认使用8040", 8040);
        new SpringApplicationBuilder(ZuulServiceApplication.class).properties("server.port="+port).run(args);
    }

}
