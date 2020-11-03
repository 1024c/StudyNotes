package com.tawe.service.edu.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName ServiceEduConfig
 * @Description 用于 MyBatis-Plus 增强
 * @Author Administrator
 * @Date 10/29/2020 12:03 PM
 * @Version 1.0
 **/
@Configuration
@EnableTransactionManagement
@MapperScan("com.tawe.service.edu.mapper")
@ComponentScan("com.tawe")
public class ServiceEduConfig {

    /** SQL 注入器 **/
    @Bean
    public ISqlInjector sqlInjector() {
        // 逻辑删除注入器
        return new LogicSqlInjector();
    }

    /** SQL 性能分析 **/
    @Bean
    @Profile({"test", "dev"})
    public PerformanceInterceptor performanceInterceptor() {
        return new PerformanceInterceptor();
    }

    /** 分页插件 **/
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /** 乐观锁插件 **/
    @Bean
    public OptimisticLockerInterceptor optimisticLockerInterceptor() {
        return new OptimisticLockerInterceptor();
    }
}
