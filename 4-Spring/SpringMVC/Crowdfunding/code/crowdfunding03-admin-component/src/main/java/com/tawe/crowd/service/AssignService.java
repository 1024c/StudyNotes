package com.tawe.crowd.service;

import com.tawe.crowd.entity.Auth;
import com.tawe.crowd.entity.Role;

import java.util.List;
import java.util.Map;

public interface AssignService {
    List<Role> getAssignedRoles(Integer adminId);
    List<Role> getUnAssignedRoles(Integer adminId);

    List<Auth> getSelectedAuths(Integer roleId);

    int addRoles(Integer adminId, List<Integer> roleIds);
    int removeRoles(Integer adminId, List<Integer> roleIds);

    int saveAuth(Map<String, List<Integer>> map);
}
