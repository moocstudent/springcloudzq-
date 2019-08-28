package com.zq.dataservice;

import com.zq.utilboot.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
@EnableCaching
public class TheCodeServiceApplication {

    public static void main(String[] args) {
        PortUtil.checkPort();
        int port = PortUtil.setPort(args, 2, "默认the-code-service启动端口8011,可启动多个,再启动建议使用8012...", 8011);
        new SpringApplicationBuilder(TheCodeServiceApplication.class).properties("server.port="+port).run(args);
    }

}
