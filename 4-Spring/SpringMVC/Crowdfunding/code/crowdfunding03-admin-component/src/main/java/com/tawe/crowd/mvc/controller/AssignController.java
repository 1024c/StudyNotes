package com.tawe.crowd.mvc.controller;

import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.entity.Role;
import com.tawe.crowd.service.AssignService;
import com.tawe.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class AssignController {

    @Autowired
    private AssignService assignService;

    @ResponseBody
    @RequestMapping("/assign/{adminId}/remove.json")
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
    @RequestMapping("/assign/{adminId}/add.json")
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

    @RequestMapping("/assign/to/role/page.html")
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
        return "assign-role";
    }
}
