package com.tawe.crowd.mvc.config;

import com.tawe.crowd.entity.Admin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;

/**
 * 针对 SpringSecurity 的 User 类对 Admin 对象进行扩展
 * @author Administrator
 */
public class SecurityAdmin extends User {

    private Admin originalAdmin;

    public SecurityAdmin(Admin originalAdmin, Collection<? extends GrantedAuthority> authorities) {
        super(originalAdmin.getUserName(), originalAdmin.getUserPswd(), authorities);
        this.originalAdmin = originalAdmin;
    }

    public Admin getOriginalAdmin() {
        return originalAdmin;
    }
}
