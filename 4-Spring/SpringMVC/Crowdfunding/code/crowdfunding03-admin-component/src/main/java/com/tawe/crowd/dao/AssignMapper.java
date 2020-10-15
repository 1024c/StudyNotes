package com.tawe.crowd.dao;

import com.tawe.crowd.entity.Auth;
import com.tawe.crowd.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssignMapper {
    List<Role> getAssignedRoles(Integer adminId);
    List<Role> getUnAssignedRoles(Integer adminId);

    List<Auth> getSelectedAuths(Integer roleId);

    int insertAssignedRolesByAdminId(@Param("adminId") Integer adminId, @Param("roleIds")List<Integer> roleIds);
    int deleteAssignedRolesByAdminId(@Param("adminId") Integer adminId, @Param("roleIds")List<Integer> roleIds);

    int insertAssignedAuthsByRoleId(@Param("roleId")Integer roleId, @Param("authIds")List<Integer> authIds);
    int deleteAssignedAuthsByRoleId(@Param("roleId")Integer roleId, @Param("authIds")List<Integer> authIds);
}
