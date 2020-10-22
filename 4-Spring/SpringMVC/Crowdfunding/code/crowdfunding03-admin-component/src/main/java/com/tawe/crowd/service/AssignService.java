package com.tawe.crowd.service;

import com.tawe.crowd.entity.Auth;
import com.tawe.crowd.entity.Role;

import java.util.List;
import java.util.Map;

public interface AssignService {
    List<Role> getAssignedRolesByAdminId(Integer adminId);
    List<Role> getUnAssignedRolesByAdminId(Integer adminId);

    List<Auth> getSelectedAuthsByRoleId(Integer roleId);

    int addRolesByAdminId(Integer adminId, List<Integer> roleIds);
    int removeRolesByAdminId(Integer adminId, List<Integer> roleIds);

    int saveAuth(Map<String, List<Integer>> map);
}
