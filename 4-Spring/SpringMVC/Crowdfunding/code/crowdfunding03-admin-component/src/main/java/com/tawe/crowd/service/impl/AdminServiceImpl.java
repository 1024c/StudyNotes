package com.tawe.crowd.service.impl;

import com.tawe.crowd.constant.CrowdConstant;
import com.tawe.crowd.dao.AdminMapper;
import com.tawe.crowd.entity.Admin;
import com.tawe.crowd.entity.AdminExample;
import com.tawe.crowd.exception.LoginFailedException;
import com.tawe.crowd.service.AdminService;
import com.tawe.crowd.util.CrowdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName AdminServiceImpl
 * @Description TODO
 * @Author Administrator
 * @Date 9/23/2020 2:00 PM
 * @Version 1.0
 **/

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public int save(Admin user) {
        return adminMapper.insert(user);
    }

    @Override
    public List<Admin> selectAll() {
        return adminMapper.selectByExample(new AdminExample());
    }

    @Override
    public Admin selectById(Integer id) {
        return adminMapper.selectByPrimaryKey(id);
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String loginPaswd) {
        // 1. 根据登录账号查询 Admin 对象;
        //     1.1 创建 AdminExample 对象;
        AdminExample adminExample = new AdminExample();
        //     1.2 创建 Criteria 对象;
        //     1.3 在 Criteria 对象中封装查询条件;
        adminExample.createCriteria().andLoginAcctEqualTo(loginAcct);
        //     1.4 调用 AdminMapper 方法执行查询;
        List<Admin> admins = adminMapper.selectByExample(adminExample);
        // 2. 判断 Admin 对象是否为空;
        if (admins == null || admins.size() == 0) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_USER_NOT_EXIST.getMsg());
        }

        // 2.1 返回 Admin 对象大于 1 则说明数据库中存在重复数据 抛异常
        if (admins.size() > 1) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_USER_NOT_UNIQUE.getMsg());
        }
        // 3. 如果 Admin 对象为 null 则抛出异常;
        Admin admin = admins.get(0);
        if (admin == null) {
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED_USER_NOT_EXIST.getMsg());
        }
        // 4. 如果  Admin 对象不为 null 则将数据库密码从 Admin 对象中取出;
        String adminPswdDB = admin.getUserPswd();

        // 5. 将表单提交的明文密码加密进行比较;
        String adminPswdForm = CrowdUtil.md5(loginPaswd);
        if (!Objects.equals(adminPswdDB, adminPswdForm)) {
            // 6. 如果密码不一致则抛出异常;
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED.getMsg());
        }
        // 7. 如果一致则返回 admin 对象;
        return admin;
    }
}
