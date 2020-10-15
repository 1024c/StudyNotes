package com.tawe.crowd.mvc.controller;

import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.entity.Auth;
import com.tawe.crowd.entity.Role;
import com.tawe.crowd.service.AssignService;
import com.tawe.crowd.service.AuthService;
import com.tawe.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class AssignController {

    @Autowired
    private AssignService assignService;

    @Autowired
    private AuthService authService;

    @ResponseBody
    @RequestMapping("/assign/role/to/auth/save.json")
    public ResultEntity<String> saveAuth(
            // 这样是无法绑定 authIds 会出类型转换异常
            // @RequestParam("roleId") Integer roleId,
            // @RequestParam("authIds") List<Integer> authIds
            @RequestBody Map<String, List<Integer>> map
            ){
        int cols = assignService.saveAuth(map);
        if (cols > 0) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed(CrowdConstant.MESSAGE_DATABASE_ERROR.getMsg());
        }

    }

    @ResponseBody
    @RequestMapping("/assign/role/to/auth/get/selected.json")
    public ResultEntity<List<Auth>> getSelectedAuths(Integer roleId) {
        List<Auth> auths = assignService.getSelectedAuths(roleId);
        return ResultEntity.succeededWithData(auths);
    }


    @ResponseBody
    @RequestMapping("/assign/role/to/auth/get/all.json")
    public ResultEntity<List<Auth>> getAllAuths() {
        List<Auth> auths = authService.getAllAuths();
        return ResultEntity.succeededWithData(auths);
    }

    // 前端通过 Ajax 请求直接发送 form 表单提交
    @ResponseBody
    @RequestMapping("/assign/admin/to/role/remove.json")
    public ResultEntity<String> removeRolesWithForm(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("roleIds") List<Integer> roleIds) {
        int cols = assignService.removeRoles(adminId, roleIds);
        if (cols > 0) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed(CrowdConstant.MESSAGE_DATABASE_ERROR.getMsg());
        }
    }

    // 前端手动收集提交的数据 使用 ajax 发送请求 => 已被上面的 form ajax 请求替代
    @ResponseBody
    @RequestMapping("/assign/admin/to/role/{adminId}/remove.json")
    public ResultEntity<String> removeRoles(
            @PathVariable("adminId") Integer adminId,
            @RequestBody List<Integer> roleIds) {
        int cols = assignService.removeRoles(adminId, roleIds);
        if (cols > 0) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed(CrowdConstant.MESSAGE_DATABASE_ERROR.getMsg());
        }

    }

    @ResponseBody
    @RequestMapping("/assign/admin/to/role/add.json")
    public ResultEntity<String> addRolesWithForm(
            @RequestParam("adminId") Integer adminId,
            @RequestParam("roleIds") List<Integer> roleIds) {
        int cols = assignService.addRoles(adminId, roleIds);
        if (cols > 0) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed(CrowdConstant.MESSAGE_DATABASE_ERROR.getMsg());
        }

    }

    @ResponseBody
    @RequestMapping("/admin/assign/to/role/{adminId}/add.json")
    public ResultEntity<String> addRoles(
            @PathVariable("adminId") Integer adminId,
            @RequestBody List<Integer> roleIds) {
        int cols = assignService.addRoles(adminId, roleIds);
        if (cols > 0) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed(CrowdConstant.MESSAGE_DATABASE_ERROR.getMsg());
        }

    }

    @RequestMapping("/assign/admin/to/role/page.html")
    public String toAssignRolePage (
            @RequestParam("adminId") Integer adminId,
            @RequestParam(value = "keyword", defaultValue = "")String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
            ModelMap modelMap
    ){
        // 1. 查询已分配角色
        List<Role> assignedRoles = assignService.getAssignedRoles(adminId);
        // 2. 查询未分配角色
        List<Role> unAssignedRoles = assignService.getUnAssignedRoles(adminId);
        // 3. 存入模型
        modelMap.addAttribute("adminId", adminId);
        modelMap.addAttribute("assignedRoles", assignedRoles);
        modelMap.addAttribute("unAssignedRoles", unAssignedRoles);
        return "admin-assign-role";
    }
}
