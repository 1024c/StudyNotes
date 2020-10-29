package com.tawe.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName ServiceEduConfig
 * @Description TODO
 * @Author Administrator
 * @Date 10/29/2020 12:03 PM
 * @Version 1.0
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.tawe.edu.mapper")
public class ServiceEduConfig {

    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
