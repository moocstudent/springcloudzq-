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
        int port = PortUtil.setPort(args, 2, "2秒输入新端口,或者启动时后缀 port=****, 默认8001端口.", 8001);
        int redisPort = 6379;
        if (NetUtil.isUsableLocalPort(redisPort)) {
            System.err.printf("检查到端口%d 未启用，判断 redis 服务器没有启动，本服务无法使用，故退出%n", redisPort);
            System.exit(1);
        }
        new SpringApplicationBuilder(DataServiceApplication.class).properties("server.port="+port).run(args);
//        SpringApplication.run(DataServiceApplication.class, args);
    }

}
