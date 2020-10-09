package com.tawe.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.entity.Role;
import com.tawe.crowd.service.RoleService;
import com.tawe.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    @RequestMapping("/role/page/get/info.json")
    public ResultEntity<PageInfo<Role>> getPageInfo(
            @RequestParam(value = "keyword", defaultValue = "")String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {

        // 1. 调用 Service 方法获取分页数据;
        PageInfo<Role> pageInfo = roleService.getPageInfo(keyword, pageNum, pageSize);
        // 2. 封装到 ResultEntity 对象中返回 (如果上面的操作抛出异常, 交给异常映射机制处理)
        return ResultEntity.succeededWithData(pageInfo);

    }

    @RequestMapping("role/page.html")
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
        return "role-page";
    }
}
