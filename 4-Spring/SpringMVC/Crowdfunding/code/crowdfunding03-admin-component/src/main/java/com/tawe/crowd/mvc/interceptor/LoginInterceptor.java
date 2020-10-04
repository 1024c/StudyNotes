package com.tawe.crowd.mvc.interceptor;

import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.entity.Admin;
import com.tawe.crowd.exception.AccessForbiddenException;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 1. 通过 request 获取 session 对象
        HttpSession session = request.getSession();
        // 2. 尝试从 Session 域中获取 Admin 对象
        Admin admin = (Admin) session.getAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN.getMsg());
        // 3. 判断 admin 对象是否为空
        if (admin == null) {
            // 4. 为空 则抛出一场
            throw new AccessForbiddenException();
        }
        // 5. 如果 Admin 对象不为 null, 则返回 true 放行
        return super.preHandle(request, response, handler);
    }
}
