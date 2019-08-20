package com.zq.dataservice;

import com.zq.utilboot.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class DataServiceApplication {

    public static void main(String[] args) {
        int port = PortUtil.setPort(args, 2, "2秒输入新端口,或者启动时后缀 port=****, 默认8001端口.", 8001);
        new SpringApplicationBuilder(DataServiceApplication.class).properties("server.port="+port).run(args);
//        SpringApplication.run(DataServiceApplication.class, args);
    }

}
