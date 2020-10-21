package com.tawe.security.config;

import com.tawe.security.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据 username 读取数据库中的用户信息
        String sql = "SELECT id, login_acct AS loginAcct, user_pswd AS userPswd FROM t_admin WHERE login_acct=?";
        List<Admin> admins = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Admin.class), username);
        if (admins.isEmpty()) {
            throw new UsernameNotFoundException("该用户: " + username + " 不存在!");
        }
        Admin admin = admins.get(0);
        // 给 Admin 设置权限信息
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        authorities.add(new SimpleGrantedAuthority("UPDATE"));

        // 把 Admin 对象和 权限信息 封装到 UserDetails 中
        return new User(username, admin.getUserPswd(), authorities);
    }
}
