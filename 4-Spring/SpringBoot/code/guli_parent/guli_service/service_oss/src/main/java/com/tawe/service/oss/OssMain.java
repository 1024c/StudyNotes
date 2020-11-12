package com.tawe.service.oss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @ClassName OssMain
 * @Description OSS 阿里云
 * @Author davidt
 * @Date 11/12/2020 1:53 PM
 * @Version 1.0
 **/
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.tawe.service.edu.mapper")
@EnableSwagger2
public class OssMain {

    public static void main(String[] args) {
        SpringApplication.run(OssMain.class, args);
    }
}
