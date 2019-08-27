package com.zq.eurekaserver;

import com.zq.utilboot.utils.PortUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {

    public static void main(String[] args) {

        int port = PortUtil.setPort(args, 2, "默认启动在8760端口,其它端口请在2秒内输入.", 8760);
        new SpringApplicationBuilder(EurekaServerApplication.class).properties("server.port="+port).run(args);
    }

}
