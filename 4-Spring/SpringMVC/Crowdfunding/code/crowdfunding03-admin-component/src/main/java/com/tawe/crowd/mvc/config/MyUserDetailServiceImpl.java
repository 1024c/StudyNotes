package com.tawe.crowd.mvc.config;

import com.tawe.crowd.entity.Admin;
import com.tawe.crowd.entity.Auth;
import com.tawe.crowd.entity.Role;
import com.tawe.crowd.service.AdminService;
import com.tawe.crowd.service.AssignService;
import com.tawe.crowd.service.AuthService;
import com.tawe.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * @author Administrator
 */
@Component
public class MyUserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private AdminService adminService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private AuthService authService;

    @Autowired
    private AssignService assignService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 1. 根据账号名称查询 Admin 对象;
        // 根据用户名查询用户信息
        Admin admin = adminService.getAdminByLoginAcct(username);
        // 2. 获取 AdminId;
        Integer adminId = admin.getId();
        // 3. 根据 AdminId 查询角色信息;
        // 根据用户名查询当前用户所有权限
        Collection<Role> roles = assignService.getAssignedRolesByAdminId(adminId);
        // 4. 根据 AdminId 查询权限信息;
        Collection<Auth> auths = new HashSet<Auth>();
        roles.forEach(role -> {
            List<Auth> selectedAuths = assignService.getSelectedAuthsByRoleId(role.getId());
            auths.addAll(selectedAuths);
        });
        // 5. 创建集合对象用来存储 GrantedAuthority;
        //authorities：存放所有用户权限
        Collection<GrantedAuthority> authorities = new HashSet<>();
        // 6. 遍历 assignedRoleList 存入角色信息;
        roles.forEach(role -> {
            // 在数据库中 添加 ROLE_ 前缀
            // 注意不要忘了加 ROLE_ 前缀;
            // String roleName = "ROLE_" + role.getRoleName();
            String roleName = role.getRoleName();
            GrantedAuthority authority = new SimpleGrantedAuthority(roleName);
            authorities.add(authority);
        });
        // 7. 遍历 authNameList 存入权限信息;
        auths.forEach(auth -> {
            String authName = auth.getAuthName();
            // 注意: 清掉为 空 "" 的 authName 不然 new SimpleGrantedAuthority 会报异常
            // "A granted authority textual representation is required"
            if (authName.isEmpty()) {
                return;
            }
            GrantedAuthority authority = new SimpleGrantedAuthority(authName);
            authorities.add(authority);
        });
        // 8. 封装 SecurityAdmin 对象;
        SecurityAdmin securityAdmin = new SecurityAdmin(admin, authorities);
        return securityAdmin;
    }
}
