package com.tawe.mybatisplus.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName MyBatisPlusConfig
 * @Description 配置类
 * @Author Administrator
 * @Date 10/27/2020 6:24 PM
 * @Version 1.0
 **/

@Configuration
@EnableTransactionManagement
// @MapperScan("com.tawe.mybatisplus.mapper")
public class MyBatisPlusConfig {

    /******** Deprecated *********/
    // @Bean
    // public OptimisticLockerInterceptor optimisticLockerInnerInterceptor() {
    //     return new OptimisticLockerInterceptor();
    // }

    // 增加乐观锁
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {

        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());

        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());

        return interceptor;
    }
}
