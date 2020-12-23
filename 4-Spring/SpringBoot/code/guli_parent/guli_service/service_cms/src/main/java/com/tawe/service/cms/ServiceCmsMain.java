package com.tawe.service.cms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * @ClassName ServiceEduMain
 * @Description TODO
 * @Author davidt
 * @Date 12/23/2020 4:50 PM
 * @Version 1.0
 **/

@SpringBootApplication
@CrossOrigin
@ComponentScan({"com.tawe"})
@MapperScan("com.tawe.service.cms.mapper")
public class ServiceCmsMain {
    public static void main(String[] args) {
        SpringApplication.run(ServiceCmsMain.class, args);
    }
}
