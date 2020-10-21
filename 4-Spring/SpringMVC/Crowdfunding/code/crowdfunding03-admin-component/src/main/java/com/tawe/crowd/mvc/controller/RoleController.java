package com.tawe.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.entity.Role;
import com.tawe.crowd.service.RoleService;
import com.tawe.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName RoleController
 * @Description TODO
 * @Author Administrator
 * @Date 10/9/2020 4:37 PM
 * @Version 1.0
 **/

@Controller
public class RoleController {

    @Autowired
    private RoleService roleService;

    @ResponseBody
    @RequestMapping("/role/removes.json")
    public ResultEntity<String> removeRoles(@RequestBody List<Integer> roleIds) {
        int col = roleService.removeRoles(roleIds);
        if (col == 0) {
            return ResultEntity.failed("");
        } else {
            return ResultEntity.succeededWithoutData();
        }
    }

    @ResponseBody
    @RequestMapping("/role/save.json")
    public ResultEntity<String> addRoleWithJson(Role role) {
        int col = 0;
        // 根据 roleID 判断是新增还是修改
        if (role.getId() == null) {
            col = roleService.addRole(role);
        } else {
            col = roleService.update(role);
        }

        if (col == 1) {
            return ResultEntity.succeededWithoutData();
        } else {
            return ResultEntity.failed("Save Error");
        }
    }

    @RequestMapping("/role/page/add.html")
    public String addRole(Role role) {
        roleService.addRole(role);
        // 重定向到分页页面，页码设置为无穷大使当前页可以显示为新增的数据
        return "redirect:/role/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    @ResponseBody
    @RequestMapping("/role/page/get/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "")String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {

        // 1. 调用 Service 方法获取分页数据;
        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);
        // // 3. 创建 Gson 对象
        // Gson gson = new Gson();
        // // 4. 将 resultEntity 转换为 JSON 对象
        // String pageInfoJson = gson.toJson(pageInfo);
        // 2. 封装到 ResultEntity 对象中返回 (如果上面的操作抛出异常, 交给异常映射机制处理)
        return ResultEntity.succeededWithData(pageInfo);
    }

    @RequestMapping("/role/page.html")
    public String getRolePage(
            // 页面上可能不提供关键词，需要使用 defaultValue 属性进行适配
            @RequestParam(value = "keyword", defaultValue = "")String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
            ModelMap modelMap
    ) {
        // 查询得到分页数据；
        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);

        // 将分页数据存入模型；
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO.getMsg(), pageInfo);
        // 返回主页面
        return "page/role/role-page";
    }
}
