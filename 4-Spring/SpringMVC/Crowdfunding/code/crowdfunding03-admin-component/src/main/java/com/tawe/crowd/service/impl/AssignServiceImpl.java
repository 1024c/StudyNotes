package com.tawe.crowd.service.impl;

import com.tawe.crowd.dao.AssignMapper;
import com.tawe.crowd.entity.Auth;
import com.tawe.crowd.entity.Role;
import com.tawe.crowd.service.AssignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class AssignServiceImpl implements AssignService {

    @Autowired
    private AssignMapper assignMapper;

    @Override
    public List<Role> getAssignedRolesByAdminId(Integer adminId) {
        return assignMapper.getAssignedRoles(adminId);
    }

    @Override
    public List<Role> getUnAssignedRolesByAdminId(Integer adminId) {
        return assignMapper.getUnAssignedRoles(adminId);
    }

    @Override
    public List<Auth> getSelectedAuthsByRoleId(Integer roleId) {
        return assignMapper.getSelectedAuths(roleId);
    }

    @Override
    // @Transactional
    public int addRolesByAdminId(Integer adminId, List<Integer> roleIds) {
        // 可以再 SQL 中写循环
        // int cols = 0;
        // for (Integer roleId : roleIds) {
        //     cols += assignMapper.insertAssignedRolesByAdminId(adminId, roleId);
        // }
        // return cols;
        return assignMapper.insertAssignedRolesByAdminId(adminId, roleIds);
    }

    @Override
    public int removeRolesByAdminId(Integer adminId, List<Integer> roleIds) {
        // 可以再 SQL 中写循环
        // int cols = 0;
        // for (Integer roleId : roleIds) {
        //     cols += assignMapper.deleteAssignedRolesByAdminId(adminId, roleId);
        // }
        // return cols;
        return assignMapper.deleteAssignedRolesByAdminId(adminId, roleIds);
    }

    @Override
    public int saveAuth(Map<String, List<Integer>> map) {
        // 1. 获取 roleId 的值
        Integer roleId = map.get("roleId").get(0);
        // 方式一: 先清空 roleId 相关的 auths, 再重新插入传过来的;
        // 缺点: 无法统计本次更新的 auths
        // // 2. 删除就关联数据
        // int cols = assignMapper.deleteAuthsByRoleId(roleId);
        // // 3. 获取 authIds
        // List<Integer> authIds = map.get("authIds");
        // // 4. 判断 authIds 是否有效
        // if (authIds == null || authIds.size() == 0) {
        //     return cols;
        // }
        // assignMapper.insertAuths(authIds);
        // return cols;

        // 方式二 没有意义/(ㄒoㄒ)/~~
        // 方式二: 先从数据库读取现有的 auths, 使用 HashSet 不可重复的特性 计算 变动的 auths
        // 将新的 auths 和原有的 auths 分别插入 HashSet 中,
        // 变动的 auths 即为 setSize - (oldSize + newSize - setSize) => oldSize + newSize - 2*setSize
        // ------oldSize-----|Removed|
        // ==================[xxxxxxx]
        //        --setSize--
        // [xxxxx]====================
        // |Added|-----newSize--------
        // 方式三: 直接计算出需要删除的 auths 和 需要新增的 auths, 两者相加即为变动量
        // 通过 removeAll 方法实现
        // 1. 获取前端传来的 authIds
        List<Integer> authIds = map.get("authIds");
        // 2. 获取 selected authIds
        List<Auth> selectedAuths = assignMapper.getSelectedAuths(roleId);
        List<Integer> selectAuthIds = new ArrayList<>();
        selectedAuths.forEach(selectAuth -> selectAuthIds.add(selectAuth.getId()));
        // // 3. 插入 HashSet 中
        // HashSet<Integer> authIdsHash = new HashSet<>(authIds);
        // authIdsHash.addAll(selectAuthIds);
        // 4. 计算变动量
        // int cols = selectAuthIds.size() + authIds.size() - 2 * authIdsHash.size();
        // 4. 计算需要删除的 authIds
        List<Integer> authIdsFromDb = new ArrayList<>(selectAuthIds);
        selectAuthIds.removeAll(authIds);
        int cols = 0;
        if (selectAuthIds.size() != 0) {
            cols += assignMapper.deleteAssignedAuthsByRoleId(roleId,selectAuthIds);
        }
        // 5. 计算需要新增的 authIds
        authIds.removeAll(authIdsFromDb);
        if (authIds.size() != 0) {
            cols += assignMapper.insertAssignedAuthsByRoleId(roleId, authIds);
        }
        return cols;





    }
}
