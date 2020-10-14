package com.tawe.crowd.service.impl;

import com.tawe.crowd.dao.AssignMapper;
import com.tawe.crowd.entity.Role;
import com.tawe.crowd.service.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignServiceImpl implements AssignService {

    @Autowired
    private AssignMapper assignMapper;

    @Override
    public List<Role> getAssignedRoles(Integer adminId) {
        return assignMapper.getAssignedRoles(adminId);
    }

    @Override
    public List<Role> getUnAssignedRoles(Integer adminId) {
        return assignMapper.getUnAssignedRoles(adminId);
    }

    @Override
    // @Transactional
    public int addRoles(Integer adminId, List<Integer> roleIds) {
        int cols = 0;
        for (Integer roleId : roleIds) {
            cols += assignMapper.insertRole(adminId, roleId);
        }
        return cols;
    }

    @Override
    public int removeRoles(Integer adminId, List<Integer> roleIds) {
        int cols = 0;
        for (Integer roleId : roleIds) {
            cols += assignMapper.deleteRole(adminId, roleId);
        }
        return cols;
    }
}
