package com.tawe.crowd.service;

import com.github.pagehelper.PageInfo;
import com.tawe.crowd.entity.Role;

/**
 * @ClassName RoleService
 * @Description TODO
 * @Author Administrator
 * @Date 10/9/2020 4:34 PM
 * @Version 1.0
 **/
public interface RoleService {
    PageInfo<Role> getPageInfo(String keyword, Integer pageNum, Integer pageSize);
    Role getRoleById(Integer id);

}
