package com.tawe.crowd.service;

import com.tawe.crowd.entity.Role;

import java.util.List;

public interface AssignService {
    List<Role> getAssignedRoles(Integer adminId);
    List<Role> getUnAssignedRoles(Integer adminId);

    int addRoles(Integer adminId, List<Integer> roleIds);

    int removeRoles(Integer adminId, List<Integer> roleIds);
}
