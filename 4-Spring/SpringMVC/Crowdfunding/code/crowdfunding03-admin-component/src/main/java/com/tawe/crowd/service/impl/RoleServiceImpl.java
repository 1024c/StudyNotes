package com.tawe.crowd.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.tawe.crowd.dao.RoleMapper;
import com.tawe.crowd.entity.Role;
import com.tawe.crowd.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName RoleServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 10/9/2020 4:35 PM
 * @Version 1.0
 **/
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {
        // 1. 开启分页功能
        PageHelper.startPage(pageNum, pageSize);
        // 2. 执行查询;
        List<Role> roleList = roleMapper.selectRoleByKeyWord(keyword);
        // 3. 封装为 PageInfo 对象
        return new PageInfo<>(roleList);
    }

    @Override
    public Role getRoleById(Integer id) {
        return roleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int addRole(Role role) {
        return roleMapper.insert(role);
    }
}
