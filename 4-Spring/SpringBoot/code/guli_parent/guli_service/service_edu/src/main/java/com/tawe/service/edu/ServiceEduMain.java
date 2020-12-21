package com.tawe.service.edu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName Teacher
 * @Description Teacher 主启动类
 * @Author Administrator
 * @Date 10/29/2020 11:57 AM
 * @Version 1.0
 **/

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
// @EnableSwagger2
public class ServiceEduMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceEduMain.class, args);
    }
}
