package com.zq.dataservice;

import cn.hutool.core.util.NetUtil;
import com.zq.utilboot.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;

@SpringBootApplication
@EnableEurekaClient  //开启eureka客户端
@EnableHystrix   //启动熔断器
@EnableCaching  //开启缓存
public class DataServiceApplication {

    public static void main(String[] args) {

        int redisPort = 6379;
        PortUtil.checkPort(redisPort);
        int port = PortUtil.setPort(args, 2, "2秒输入新端口,或者启动时后缀 port=****, 默认8001端口.", 8001);

        new SpringApplicationBuilder(DataServiceApplication.class).properties("server.port="+port).run(args);
//        SpringApplication.run(DataServiceApplication.class, args);
    }

}
