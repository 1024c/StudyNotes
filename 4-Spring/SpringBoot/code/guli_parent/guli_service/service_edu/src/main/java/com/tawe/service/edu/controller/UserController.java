package com.tawe.service.edu.controller;

import com.tawe.common.utils.ResultEntity;
import com.tawe.service.edu.dtu.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author davidt
 * @Date 11/10/2020 2:18 PM
 * @Version 1.0
 **/
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @RequestMapping("login")
    public ResultEntity userLogin() {
        return ResultEntity.ok().data("token", "admin-token");
    }

    @RequestMapping("info")
    public ResultEntity userInfo() {
        HashMap<String, Object> userMap = new HashMap<>();
        userMap.put("name", "Super Admin");
        userMap.put("introduction", "I am a super administrator");
        userMap.put("roles", new String[]{"admin"});
        userMap.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        return ResultEntity.ok().data(userMap);
    }

    @RequestMapping("logout")
    public ResultEntity userLogout() {
        return ResultEntity.ok().data("data", "success");
    }
}
