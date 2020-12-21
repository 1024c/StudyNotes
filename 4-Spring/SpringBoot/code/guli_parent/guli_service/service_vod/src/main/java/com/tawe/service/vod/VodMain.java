package com.tawe.service.vod;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ClassName VodMain
 * @Description TODO
 * @Author davidt
 * @Date 12/16/2020 5:54 PM
 * @Version 1.0
 **/

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@ComponentScan(basePackages={"com.tawe"})
public class VodMain {
    public static void main(String[] args) {
        SpringApplication.run(VodMain.class, args);
    }
}
