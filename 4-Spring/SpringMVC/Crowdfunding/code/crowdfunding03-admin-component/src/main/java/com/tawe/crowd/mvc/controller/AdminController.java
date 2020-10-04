package com.tawe.crowd.mvc.controller;

import com.github.pagehelper.PageInfo;
import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.entity.Admin;
import com.tawe.crowd.exception.LoginAcctAlreadyInUseException;
import com.tawe.crowd.exception.LoginFailedException;
import com.tawe.crowd.exception.SystemErrorException;
import com.tawe.crowd.service.AdminService;
import com.tawe.crowd.util.ResultEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName AdminController
 * @Description TODO
 * @Author Administrator
 * @Date 9/23/2020 3:27 PM
 * @Version 1.0
 **/
@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("admin/page/edit/{adminId}.html")
    public String editAdmin(Admin admin,
            @RequestParam(value = "keyword", defaultValue = "")String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {
        int col = adminService.saveSelective(admin);
        return "redirect:/admin/page.html?keyword=" + keyword
                + "&pageNum=" + pageNum
                + "&pageSize=" + pageSize;
    }


    @RequestMapping("/admin/to/edit/page.html")
    public String toEditPage(@RequestParam("adminId")Integer id, ModelMap modelMap) {
        // 1. 根据 id 查询待更新的 Admin 对象；
        Admin admin = adminService.selectById(id);
        // 2. 将 Admin 对象存入模型供 jsp 使用；
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN.getMsg(), admin);
        return "admin-edit";
    }

    @RequestMapping("/admin/page/add.html")
    public String addAdmin(Admin admin) throws LoginAcctAlreadyInUseException {
        adminService.save(admin);
        // 重定向到分页页面，页码设置为无穷大使当前页可以显示为新增的数据
        return "redirect:/admin/page.html?pageNum=" + Integer.MAX_VALUE;
    }

    @RequestMapping("/admin/page/remove/{adminId}.html")
    public String removeAdmin(
            @PathVariable("adminId") Integer id,
            @RequestParam(value = "keyword", defaultValue = "")String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize) {
        int col = adminService.removeById(id);
        return "redirect:/admin/page.html?keyword=" + keyword
                + "&pageNum=" + pageNum
                + "&pageSize=" + pageSize;
    }

    @RequestMapping("/admin/page.html")
    public String getAdminPage(
            // 页面上可能不提供关键词，需要使用 defaultValue 属性进行适配
            @RequestParam(value = "keyword", defaultValue = "")String keyword,
            @RequestParam(value = "pageNum", defaultValue = "1")Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "5")Integer pageSize,
            ModelMap modelMap
    ) {
        // 查询得到分页数据；
        PageInfo<Admin> adminPage = adminService.getAdminPage(keyword, pageNum, pageSize);
        // 将分页数据存入模型；
        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO.getMsg(), adminPage);
        // 返回主页面
        return "admin-page";
    }

    @RequestMapping("/admin/do/logout.html")
    public String doLogout(HttpSession session) {
        session.invalidate();
        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct,
                          @RequestParam("loginPaswd") String loginPaswd,
                          HttpSession session) {
        // 调用 Service 方法执行登录检查;

        Admin admin = adminService.getAdminByLoginAcct(loginAcct, loginPaswd);

        // 返回 Admin 对象说明登录成功;
        // 如果账号, 密码不正确则会抛出异常;
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED.getMsg());
        }

        // 登录成功将返回的  admin 对象加入 Session 域中
        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN.getMsg(), admin);

        // 直接返回页面会导致,后续每次刷新 main 页面都会重新提交 登录请求
        // return "admin-main";
        // 使用重定向解决该问题, 不可直接 redirect 页面 admin-main.jsp 在 WEB-INF 下不可访问
        // return "redirect:admin-main"
        // 通过 controller 请求重定向, 此处由于是直接返回页面,可以在 xml 中配置 mvc:view-controller
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("/test/ssm.html")
    public String ssmIndex(ModelMap modelMap) throws LoginFailedException {
        List<Admin> admins = adminService.selectAll();
        modelMap.addAttribute("admins", admins);
        // throw new LoginFailedException();
        return "target";
    }

    @RequestMapping("/test/login_exception.html")
    public String testException() throws LoginFailedException {
        throw new LoginFailedException();
    }

    @RequestMapping("/test/sys_exception.html")
    public String testSystemErrorException() throws SystemErrorException {
        throw new SystemErrorException("system error message!");
    }

    @ResponseBody
    @RequestMapping("/test/ajax.json")
    public ResultEntity<List<Admin>> getUserById(@RequestBody Integer[] ids) {
        List<Admin> admins = new ArrayList<>();
        for (Integer id : ids) {
            admins.add(adminService.selectById(id));
        }
        return ResultEntity.succeededWithData(admins);
    }

    @ResponseBody
    @RequestMapping("/test/ajax_exception.json")
    public ResultEntity<List<Admin>> testAjaxException() throws SystemErrorException {
        throw new SystemErrorException("system error message!");
    }
}
