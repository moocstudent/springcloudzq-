package com.zq.thirdpartindexdataproj;

import com.zq.utilboot.utils.PortUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ThirdPartIndexDataProjApplication {

    public static void main(String[] args) {
        PortUtil.checkPort();
        int port = PortUtil.setPort(args, 2, "第三方数据默认端口8090启动", 8090);
        new SpringApplicationBuilder(ThirdPartIndexDataProjApplication.class).properties("server.port="+port).run(args);
    }

}
