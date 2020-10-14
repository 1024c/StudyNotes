package com.tawe.crowd.dao;

import com.tawe.crowd.entity.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssignMapper {
    List<Role> getAssignedRoles(Integer adminId);
    List<Role> getUnAssignedRoles(Integer adminId);

    int insertRole(@Param("adminId") Integer adminId, @Param("roleId")Integer roleId);

    int deleteRole(@Param("adminId") Integer adminId, @Param("roleId")Integer roleId);
}
