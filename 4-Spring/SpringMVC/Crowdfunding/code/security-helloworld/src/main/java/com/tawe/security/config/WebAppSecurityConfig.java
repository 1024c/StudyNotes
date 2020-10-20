package com.tawe.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;

import javax.sql.DataSource;


/**
 * @author Administrator
 */
@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);

        // 注入 datasource 到 JdbcTokenRepository 中
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        /// jdbcTokenRepository.initDao();

        http
                .authorizeRequests()
                // 设置 /layui/** & "/index.jsp" 不需要拦截认证
                .antMatchers("/layui/**", "/index.jsp")
                .permitAll()
                // 设置 /level1/** 需要 level1 角色
                //     /level2/** 需要 level2 角色
                .antMatchers("/level1/**")
                .hasRole("level1")
                .antMatchers("/level2/**")
                .hasRole("level2")
                // 设置任何请求都必须走 认证 - 除去上面已经单独设置的
                .anyRequest().authenticated()
                // 设置登录页面
                .and()
                .formLogin()
                .loginPage("/index.jsp")
                .permitAll()
                // 设置登录请求页面以及跳转页面
                .loginProcessingUrl("/do/login.html")
                .usernameParameter("loginacct")
                .passwordParameter("credential")
                .defaultSuccessUrl("/main.html")
                // 设置登出请求
                .and()
                .logout()
                .logoutUrl("/do/logout.html")
                // 设置异常的显示页面
                .and()
                .exceptionHandling()
                .accessDeniedPage("/to/no/auth/page.html")
                // 开启 remember-me 功能
                .and()
                .rememberMe()
                // 将 remember-me 的 cookie 存入数据库
                .tokenRepository(jdbcTokenRepository);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // super.configure(auth);
        auth
                .inMemoryAuthentication()
                .withUser("tom").password("{noop}123").roles("ADMIN", "level1")
                .and()
                .withUser("david").password("{noop}qwe").authorities("SAVE", "EDIT", "level2");
    }
}
