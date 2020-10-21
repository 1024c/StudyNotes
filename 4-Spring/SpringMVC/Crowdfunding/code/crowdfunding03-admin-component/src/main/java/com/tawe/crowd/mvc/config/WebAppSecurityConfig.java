package com.tawe.crowd.mvc.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebAppSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("tom").password("{noop}123123").roles("ADMIN");
        // auth
        //         .userDetailsService()
        //         .passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                // 针对静态资源进行设置，无条件访问
                // 以及登录页
                .antMatchers("/static/**", "/admin/to/login/page.html")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                // 开启表单登录的功能
                .formLogin()
                // 指定登录页面
                .loginPage("/admin/to/login/page.html")
                // 指定处理登录请求的地址
                .loginProcessingUrl("/security/do/login.html")
                // 指定登录成功后前往的地址
                .defaultSuccessUrl("/admin/to/main/page.html")
                // 账号的请求参数名称
                .usernameParameter("loginAcct")
                // 密码的请求参数名称;
                .passwordParameter("loginPswd")
                .and()
                // 开启退出登录功能
                .logout()
                // 指定退出登录地址
                .logoutUrl("/seucrity/do/logout.html")
                // 指定退出成功以后前往的地址
                .logoutSuccessUrl("/admin/to/login/page.html");
    }
}
